package manred.top.nettyrpc.core.client;

import manred.top.nettyrpc.core.protocol.RpcRequest;
import manred.top.nettyrpc.use.IHelloService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 *
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 17:20
 */
class RpcClientTest {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        client.start("127.0.0.1", 18866);
        RpcRequest request = new RpcRequest();
        request.setRequestId("1");
        request.setClassName(IHelloService.class.getName());
        request.setMethodName("hello");
        request.setParameterTypes(new Class<?>[]{String.class});
        request.setParameters(new Object[]{"manred"});
        client.rpc(request);
    }
}