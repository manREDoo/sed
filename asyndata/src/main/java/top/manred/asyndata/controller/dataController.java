package top.manred.asyndata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.manred.asyndata.data.IRecordManager;
import top.manred.asyndata.data.Record;

@RestController()
@RequestMapping("/data")
public class dataController {
    @Autowired
    private IRecordManager recordManager;

    @GetMapping("/save")
    public Record save(){
        Record record = new Record("我是一条记录");
        recordManager.saveAsyn(record);
        return record;
    }

    @GetMapping("/update")
    public Record update(){
        Record record = new Record("我是一条记录");
        recordManager.updateAsyn(record);
        return record;
    }
}
