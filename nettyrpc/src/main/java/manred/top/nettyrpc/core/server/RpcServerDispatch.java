package manred.top.nettyrpc.core.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorGroup;
import manred.top.nettyrpc.core.protocol.RpcRequest;
import manred.top.nettyrpc.core.protocol.RpcResponse;
import org.springframework.beans.BeansException;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 服务调度器
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 14:54
 */
@Service
public class RpcServerDispatch implements ApplicationContextAware {

    /**
     * 缓存类名与对象
     */
    private Map<String, Object> handlerMap = new HashMap<>();

    /**
     * 映射channel和事件处理器
     */
    private Map<Channel, EventExecutor> channelToExecutor = new ConcurrentHashMap<>();

    /**
     * 线程管理
     */
    private EventExecutorGroup executors = new DefaultEventExecutorGroup(Runtime.getRuntime().availableProcessors());

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RpcService.class);
        if (serviceBeanMap != null) {
            for (Object serviceBean : serviceBeanMap.values()) {
                String interfaceName = serviceBean.getClass().getAnnotation(RpcService.class).value().getName();
                handlerMap.put(interfaceName, serviceBean);
            }
        }
    }

    /**
     * 处理请求
     * @param ctx
     * @param request
     */
    public void processRequest(ChannelHandlerContext ctx, RpcRequest request){
        EventExecutor executor = get(ctx.channel());
        executor.execute(() -> {
            dispatch(ctx, request);
        });
    }

    private void dispatch(ChannelHandlerContext ctx, RpcRequest request){
        RpcResponse response = new RpcResponse();
        response.setRequestId(request.getRequestId());
        try{
            Object result = handler(request);
            response.setResult(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        ctx.writeAndFlush(response);
    }

    private Object handler(RpcRequest request) throws InvocationTargetException {
        String className = request.getClassName();
        Object serviceBean = handlerMap.get(className);

        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        //Cglib reflect
        FastClass fastClass = FastClass.create(serviceClass);
        int methodIndex = fastClass.getIndex(methodName, parameterTypes);

        return fastClass.invoke(methodIndex, serviceBean, parameters);
    }

    private EventExecutor get(Channel channel){
        if (!channelToExecutor.containsKey(channel)){
            channelToExecutor.put(channel,executors.next());
        }
       return channelToExecutor.get(channel);
    }

    /**
     * 移除映射
     * @param channel
     */
    public void remove(Channel channel){
        channelToExecutor.remove(channel);
    }
}
