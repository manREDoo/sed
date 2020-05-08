package manred.top.nettyrpc.core.protocol;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * rpc处理结果返回对象
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/8 11:01
 */
@Getter
@Setter
public class RpcResponse {
    private String requestId;
    private String error;
    private Object result;
}
