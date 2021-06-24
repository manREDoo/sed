package manred.top.nettyrpc.core.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import manred.top.nettyrpc.core.protocol.RpcResponse;

import java.nio.channels.SocketChannel;

/**
 * <p>
 * 客户端处理器
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 17:05
 */
public class RpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {
        System.out.println(rpcResponse.getResult().toString());
    }
}
