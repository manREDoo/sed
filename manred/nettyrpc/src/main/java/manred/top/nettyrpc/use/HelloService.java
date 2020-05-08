package manred.top.nettyrpc.use;

import manred.top.nettyrpc.core.server.RpcService;

/**
 * <p>
 *
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 16:55
 */
@RpcService(IHelloService.class)
public class HelloService implements IHelloService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
