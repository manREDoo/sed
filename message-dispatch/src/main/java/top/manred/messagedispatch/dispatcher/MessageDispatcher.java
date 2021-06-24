package top.manred.messagedispatch.dispatcher;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageDispatcher implements ApplicationContextAware, InitializingBean, IMessageDispatcher {

    private static ApplicationContext applicationContext;
    private static final Map<Integer,MessageHandlerHolder> handlers = new HashMap<>();
    // 初始化标记
    private volatile boolean hasInit = false;

    public void init(){
        // 从sping上下文取出所有消息处理器
        Map<String, AbstractHandler> handlerMap = applicationContext.getBeansOfType(AbstractHandler.class);
        for (AbstractHandler handler : handlerMap.values()){
            Class<?> clazz = handler.getClass();
            Method[] methods = clazz.getMethods();
            for (Method method : methods){
                if (checkCmdParams(method)){
                    continue;
                }
                MessageHandlerHolder holder = new MessageHandlerHolder(handler,method);
                handlers.put(holder.getCmdId(),holder);
            }
        }
        hasInit = true;
    }

    private boolean checkCmdParams(Method method) {
        if (method.getAnnotation(Cmd.class) == null){
            return true;
        }
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.init();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 实现消息分发
     * @param command
     * @return
     */
    @Override
    public CommandResult dispatch(Command command){
        Object result = null;
        try{
            if (!hasInit){
                init();
            }
            MessageHandlerHolder holder = handlers.get(command.getHead());
            Object[] args = this.prepareArguments(holder.getMethod(), command);
            result = holder.method.invoke(holder.getHandler(), args);
        }catch (Exception e){
            e.printStackTrace();
        }
        return (CommandResult) result;
    }

    /**
     * 参数包装
     */
    private Object[] prepareArguments(Method method, Command command) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] args = new Object[parameterTypes.length];
        String[] bodies = command.getBodies();
        for (int i = 0; i < args.length; i++) {
            if (parameterTypes[i].equals(int.class) || parameterTypes[i].equals(Integer.class)) {
                args[i] = Integer.valueOf(bodies[i]);
            } else if (parameterTypes[i].equals(String.class)) {
                args[i] = bodies[i];
            } else if (parameterTypes[i].equals(long.class) || parameterTypes[i].equals(Long.class)) {
                args[i] = Long.valueOf(bodies[i]);
            } else if (parameterTypes[i].equals(float.class) || parameterTypes[i].equals(Float.class)) {
                args[i] = Float.valueOf(bodies[i]);
            } else if (parameterTypes[i].equals(double.class) || parameterTypes[i].equals(Double.class)) {
                args[i] = Double.valueOf(bodies[i]);
            } else if (parameterTypes[i].equals(byte.class) || parameterTypes[i].equals(Byte.class)) {
                args[i] = Byte.valueOf(bodies[i]);
            } else if (parameterTypes[i].equals(short.class) || parameterTypes[i].equals(Short.class)) {
                args[i] = Short.valueOf(bodies[i]);
            }
        }
        return args;
    }


    @Getter
    private class MessageHandlerHolder {
        private AbstractHandler handler;
        private Method method;
        private Cmd cmd;

        public MessageHandlerHolder(AbstractHandler h, Method m) {
            this.method = m;
            this.cmd = m.getAnnotation(Cmd.class);
            this.handler = h;
        }

        public int getCmdId() {
            return cmd.id();
        }

    }
}
