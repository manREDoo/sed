package top.manred.asyndata.asyn;

public interface PersistentProvider<T extends AsyncObject> {
    void save(T asyncObject) throws Exception;

    void update(T asyncObject) throws Exception;
}