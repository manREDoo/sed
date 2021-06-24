package manred.top.nettyrpc.core.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import manred.top.nettyrpc.core.protocol.RpcRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 处理Rpc请求
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 14:19
 */
@ChannelHandler.Sharable
@Component
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    @Autowired
    private RpcServerDispatch dispatch;

    @Override
    protected void channelRead0(ChannelHandlerContext cxt, RpcRequest request) throws Exception {
        dispatch.processRequest(cxt, request);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        dispatch.remove(ctx.channel());
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
