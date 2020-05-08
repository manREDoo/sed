package manred.top.nettyrpc.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import manred.top.nettyrpc.core.protocol.RpcRequest;

import javax.xml.ws.Response;

/**
 * <p>
 * 客户端
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 17:04
 */
public class RpcClient {

    private EventLoopGroup group = new NioEventLoopGroup();
    private ChannelFuture future;


    public void start(String host, int port){
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new RpcClientInitializer());
        try {
            future = bootstrap.connect(host, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            group.shutdownGracefully();
        }
    }

    public void stop(){
        future.channel().close();
        group.shutdownGracefully();
    }

    public void rpc(RpcRequest request){
        future.channel().writeAndFlush(request);
    }
}
