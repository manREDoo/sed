package manred.topo.server.framework.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {
    private static final String WEBSOCKET_PATH = "/websocket";
    private final SslContext context = configureTLS();

    @Value("${cerFilePath}")
    public static String cerFilePath;
    @Value("${keyFilePath}")
    public static String keyFilePath;

    private SslContext configureTLS() {
/*        try{
            File cer = new File(cerFilePath);
            File key = new File(keyFilePath);
            SslContext sslc =null;
            if(cer.exists() && key.exists()){
                try{
                    sslc = SslContextBuilder.forServer(cer, key).build();
                }catch(IllegalArgumentException e){
                    e.printStackTrace();
                }
            }else{
                if(!cer.exists()){
                    System.err.println("load WSS failed,cer file not exist!!");
                }else{
                    System.err.println("cer file : " + cer.getAbsolutePath());
                }

                if(!key.exists()){
                    System.err.println("load WSS failed,key file not exist!!");
                }else{
                    System.err.println("key file : " + key.getAbsolutePath());
                }
            }
            return sslc;
        }catch(Exception e){
            e.printStackTrace();
        }*/
        return null;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (context != null){
            pipeline.addLast(context.newHandler(ch.alloc()));
        }

        //http协商协议处理 start
        pipeline.addLast(new HttpServerCodec());
        //协商协议很小，所以这个handler不用给太大的最大字节限制
        pipeline.addLast(new HttpObjectAggregator(1000));
        pipeline.addLast(new HttpRequestHandler(WEBSOCKET_PATH));
        //http协商协议处理 end

        //websocket协议，设置最大字节，这里设置为Int的最大值，没有设置的话默认65535，如果协议大小超过限制，会报异常并关闭链接
        pipeline.addLast(new MyWebSocketServerProtocolHandler(WEBSOCKET_PATH,null,false,Integer.MAX_VALUE));
        pipeline.addLast(new BinaryWebSocketFrameHandler());
        pipeline.addLast(new TextWebSocketFrameHandler());

        pipeline.addLast(new ProtoDecoder());
        pipeline.addLast(new GameServerEncoder());
        pipeline.addLast(new GameServerHandler());
    }

    public boolean isUseWss() {
        return context != null;
    }
}
