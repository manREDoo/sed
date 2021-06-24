package top.manred.eventbus.eventhandler;

import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;
import top.manred.eventbus.event.HelloEvent;

@Component
public class HelloEventHandler implements IEventBusHandler {

    @Subscribe
    public void helloEventListener(HelloEvent event){
        System.out.println("收到的数据："+event.getData().toString());
    }
}
