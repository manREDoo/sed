package manred.top.nettyrpc.core.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * Rpc请求参数对象
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 10:58
 */
@Getter
@Setter
@ToString
public class RpcRequest {
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 请求接口类
     */
    private String className;
    /**
     * 请求接口方法
     */
    private String methodName;
    /**
     * 请求方法参数类型
     */
    private Class<?>[] parameterTypes;
    /**
     * 请求方法参数
     */
    private Object[] parameters;
}
