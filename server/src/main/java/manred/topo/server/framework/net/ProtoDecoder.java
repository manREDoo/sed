package manred.topo.server.framework.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

@ChannelHandler.Sharable
public class ProtoDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while (in.readableBytes() > 4){
            in.markReaderIndex();
            int length = in.readInt();
            // 如果可读长度小于body长度，恢复读指针，退出。
            if (in.readableBytes() < length){
                in.resetReaderIndex();
                return;
            }
            // 获取包头中的protobuf类型
            short packetId = in.readShort();
            // 读取body
            byte[] dst = new byte[length - 2];
            in.readBytes(dst);
            BaseMessage result = new BaseMessage(packetId, dst);
            out.add(result);
        }
    }
}
