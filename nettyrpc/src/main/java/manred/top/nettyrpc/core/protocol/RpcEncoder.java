package manred.top.nettyrpc.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <p>
 *  对象编码器
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 10:53
 */
public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if (genericClass.isInstance(in)){
            byte[] data = SerializationUtil.serialize(in);
            System.out.println(SerializationUtil.deserialize(data, genericClass));
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
