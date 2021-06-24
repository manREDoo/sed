package manred.top.nettyrpc.core.protocol;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import manred.top.nettyrpc.use.IHelloService;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 对象序列化工具
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 10:51
 */
public class SerializationUtil {

    private static Map<Class<?>, Schema<?>> cacheSchema = new ConcurrentHashMap<>();

    private static Objenesis objenesis = new ObjenesisStd(true);

    private static <T> Schema<T> getSchema(Class<T> cls){
        Schema<T> schema = (Schema<T>) cacheSchema.get(cls);
        //创建唯一实例
        if (schema == null){
            synchronized (SerializationUtil.class){
                schema = (Schema<T>) cacheSchema.get(cls);
                if (schema == null){
                    schema = RuntimeSchema.createFrom(cls);
                    cacheSchema.put(cls, schema);
                }
            }
        }
        return schema;
    }
    //反序列化对象
    public static <T> T deserialize(byte[] data, Class<T> cls) {
        try {
            T message = objenesis.newInstance(cls);
            Schema<T> schema = getSchema(cls);
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //序列化对象
    public static <T> byte[] serialize(T obj) {
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            buffer.clear();
        }
        return new byte[0];
    }

    public static void main(String[] args) {
        RpcRequest request = new RpcRequest();
        request.setRequestId("1");
        request.setClassName(IHelloService.class.getName());
        request.setMethodName("hello");
        request.setParameterTypes(new Class<?>[]{String.class});
        request.setParameters(new Object[]{"manred"});
        System.out.println(SerializationUtil.deserialize(SerializationUtil.serialize(request), RpcRequest.class).toString());
    }
}
