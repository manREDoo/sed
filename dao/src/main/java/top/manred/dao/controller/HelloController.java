package top.manred.dao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.manred.dao.data.dao.RecordDao;
import top.manred.dao.data.pojo.Record;

@Controller
@RestController
public class HelloController {
    @Autowired
    private RecordDao recordDao;
    @GetMapping("/hello")
    public void hello(String name){
        recordDao.getOne(name);
        Record record = new Record();
        record.setId(1);
        record.setName(name);
        record.setTime(24);
        recordDao.update(record);
    }
}
