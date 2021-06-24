package manred.top.nettyrpc.core.server;

import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 标记RPC服务
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 14:24
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface RpcService {
    //服务的接口类
    Class<?> value();
}
