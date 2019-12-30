package manred.topo.server.framework.net;

import com.google.protobuf.MessageLite;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutor;
import manred.topo.server.framework.thread.LogicExecutorGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@ChannelHandler.Sharable
public class GameServerHandler extends SimpleChannelInboundHandler<BaseMessage> {

    private static final ConcurrentMap<Channel, EventExecutor> channelToExecutor = new ConcurrentHashMap<>();

    private static final ConcurrentMap<Channel, ScheduledFuture<?>> hBFutureMap = new ConcurrentHashMap<>();

    private static Map<Short, MessageLite> packetId2MessageLite;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMessage msg) throws Exception {
        MessageLite lite = null;
        lite = decodeBody(msg.getPacketId(), msg.getBytes());
        if (lite == null) {
            ctx.channel().close();
            return;
        }
        Channel channel = ctx.channel();
        EventExecutor singleExecutor = channelToExecutor.get(channel);
        if (singleExecutor == null) {
            singleExecutor = LogicExecutorGroup.nextExecutor();
            EventExecutor tmpExecutor = channelToExecutor.putIfAbsent(channel, singleExecutor);
            if (tmpExecutor != null) {
                singleExecutor = tmpExecutor;
            } else {// 第一次关联的时候开始心跳处理
                /*ScheduledFuture<?> future = singleExecutor.scheduleAtFixedRate(session, 0, 500, TimeUnit.MILLISECONDS);
                hBFutureMap.put(channel, future);*/
            }
        }
        singleExecutor.execute(() -> {
            //真实处理请求
        });
    }

    private MessageLite decodeBody(short packetId, byte[] bytes)throws Exception {
        MessageLite prototype = null;
        if (prototype != null) {
            MessageLite lite = prototype.getParserForType().parseFrom(bytes);
            return lite;
        }
        return null;
    }

}
