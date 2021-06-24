package manred.topo.server.framework.net;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import java.util.List;

@ChannelHandler.Sharable
public class GameServerEncoder extends MessageToMessageDecoder<MessageLiteOrBuilder> {
    @Override
    protected void decode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out) throws Exception {
        try {
            MessageLite m = null;
            if (m instanceof MessageLite){
                m = (MessageLite)msg;
            }
            if (msg instanceof MessageLite.Builder){
                m = ((MessageLite.Builder)msg).build();
            }

            Short messageId = 1; //proto的class文件缓存id

            ByteBuf buf = Unpooled.buffer();
            byte[] byteArray = m.toByteArray();
            buf.writeInt(byteArray.length + 2);
            buf.writeShort(messageId);
            buf.writeBytes(byteArray);

            BinaryWebSocketFrame frame = new BinaryWebSocketFrame(buf);
            out.add(frame);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
