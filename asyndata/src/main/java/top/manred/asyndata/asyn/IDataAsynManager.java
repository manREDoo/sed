package top.manred.asyndata.asyn;

public interface IDataAsynManager {
    /**
     * 放入更新对列中,异步执行
     * @param data
     */
    void saveAsync(AsyncObject data);

    /**
     * 放入更新对列中,异步执行
     * @param data
     */
    void updateAsync(AsyncObject data);
}
