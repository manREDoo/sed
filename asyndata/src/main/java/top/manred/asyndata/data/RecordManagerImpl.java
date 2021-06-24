package top.manred.asyndata.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.manred.asyndata.asyn.IDataAsynManager;
import top.manred.asyndata.asyn.PersistentProvider;

@Component
public class RecordManagerImpl implements IRecordManager, PersistentProvider<Record> {
    @Autowired
    private IDataAsynManager dataManager;

    @Override
    public void save(Record asyncObject) throws Exception {
        System.out.println("保存了一条数据：" + asyncObject.toString());
    }

    @Override
    public void update(Record asyncObject) throws Exception {
        System.out.println("更新了一条数据：" + asyncObject.toString());
    }

    @Override
    public void saveAsyn(Record record) {
        try {
            dataManager.saveAsync(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAsyn(Record record) {
        try {
            dataManager.updateAsync(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
