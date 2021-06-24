package top.manred.dao.data.dao;

import top.manred.dao.core.BaseService;
import top.manred.dao.core.SQL;
import top.manred.dao.core.SQLParam;
import top.manred.dao.data.pojo.Record;

public interface RecordDao extends BaseService {
    @SQL("select * from record where name = :name")
    public Record getOne(@SQLParam("name")String name);

    @SQL("update Record set name=:r.name, time=:r.time where id=:r.id ")
    public void update(@SQLParam("r")Record record);
}
