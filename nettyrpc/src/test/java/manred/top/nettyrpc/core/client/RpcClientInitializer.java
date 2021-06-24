package manred.top.nettyrpc.core.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import manred.top.nettyrpc.core.protocol.RpcDecoder;
import manred.top.nettyrpc.core.protocol.RpcEncoder;
import manred.top.nettyrpc.core.protocol.RpcRequest;
import manred.top.nettyrpc.core.protocol.RpcResponse;

/**
 * <p>
 *
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 17:11
 */
public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(65536,0,4,0,0));
        pipeline.addLast(new RpcDecoder(RpcResponse.class));
        pipeline.addLast(new RpcEncoder(RpcRequest.class));
        pipeline.addLast(new RpcClientHandler());
    }
}
