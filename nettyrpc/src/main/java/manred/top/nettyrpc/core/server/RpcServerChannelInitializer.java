package manred.top.nettyrpc.core.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import manred.top.nettyrpc.core.protocol.RpcDecoder;
import manred.top.nettyrpc.core.protocol.RpcEncoder;
import manred.top.nettyrpc.core.protocol.RpcRequest;
import manred.top.nettyrpc.core.protocol.RpcResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 组装RpcServerHandler的handler
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 15:54
 */
@Component
public class RpcServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private RpcServerHandler rpcServerHandler;

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(65536,0,4,0,0));
        pipeline.addLast(new RpcDecoder(RpcRequest.class));
        pipeline.addLast(new RpcEncoder(RpcResponse.class));
        pipeline.addLast(rpcServerHandler);
    }
}
