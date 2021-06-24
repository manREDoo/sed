package top.manred.eventbus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.manred.eventbus.event.HelloEvent;
import top.manred.eventbus.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(String name){
        HelloEvent event = new HelloEvent();
        Map<String, Object> data = new HashMap<>();
        data.put("hello",name);
        event.setData(data);
        EventBus.post(event);
        return "hello " + name +" !";
    }
}
