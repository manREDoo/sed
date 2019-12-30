package manred.topo.server.framework.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public final class NetServer {
    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    private ChannelFuture serverSocketFuture;



    @Value("${netty.port}")
    private int port ;

    public void start(){
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            WebSocketServerInitializer initializer = new WebSocketServerInitializer();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(initializer)
                    .option(ChannelOption.SO_REUSEADDR,true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.bind(port);
            future.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    Throwable cause = future.cause();
                    if(cause == null){
                        System.out.println("curTime: " + new Date() + " WebSocket Addr In:[" + (initializer.isUseWss() ? "wss" : "ws") + "://Ip:" + port + "/websocket]"
                                + " starts successfully.");
                    }else{
                        System.err.println("curTime: " + new Date() + " WebSocket Addr In:[" + (initializer.isUseWss() ? "wss" : "ws") + "://Ip:" + port + "/websocket]"
                                + " starts failed.");
                        System.exit(1);
                    }
                }
            });
        }catch (Exception e){
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}