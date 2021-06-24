package manred.topo.server.framework.loadable;

/**
 * Config数据接口
 *
 * Created by Administrator on 2018/12/26.
 */
public interface ConfigData<T> {

    /**
     * 重写数据
     *
     * @param config
     * @param <T>
     */
    public void reloadConfig(T config);
}
