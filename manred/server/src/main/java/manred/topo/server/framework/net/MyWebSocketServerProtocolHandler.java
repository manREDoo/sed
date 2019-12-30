package manred.topo.server.framework.net;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * 继承WebSocketServerProtocolHandler类，因为这个类在处理异常事件后，没有把事件继续往next handler冒泡，导致后面的handler无法接受到异常事件。
 * MyWebSocketServerProtocolHandler在父类处理完异常事件后，继续往next触发异常的执行
 */
@ChannelHandler.Sharable
public class MyWebSocketServerProtocolHandler extends WebSocketServerProtocolHandler {
    public MyWebSocketServerProtocolHandler(String websocketPath) {
        super(websocketPath);
    }

    public MyWebSocketServerProtocolHandler(String websocketPath, String subprotocols, boolean allowExtensions, int maxFrameSize,
                                            boolean allowMaskMismatch) {
        super(websocketPath, subprotocols, allowExtensions, maxFrameSize, allowMaskMismatch);
    }



    public MyWebSocketServerProtocolHandler(String websocketPath, String subprotocols, boolean allowExtensions, int maxFrameSize) {
        super(websocketPath, subprotocols, allowExtensions, maxFrameSize);
    }



    public MyWebSocketServerProtocolHandler(String websocketPath, String subprotocols, boolean allowExtensions) {
        super(websocketPath, subprotocols, allowExtensions);
    }



    public MyWebSocketServerProtocolHandler(String websocketPath, String subprotocols) {
        super(websocketPath, subprotocols);
    }

    /**
     * 在原来的方法中，链接被关闭，然后异常cause没有再处理，所以没有地方显示，无法看到输出
     * 重写这个方法，让其他的handler可以继续处理这个异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        ctx.fireExceptionCaught(cause);
    }
}

