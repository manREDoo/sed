package top.manred.eventbus.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import top.manred.eventbus.event.AbstractEvent;
import top.manred.eventbus.eventhandler.IEventBusHandler;

import javax.servlet.AsyncEvent;
import java.util.Map;
import java.util.concurrent.Executors;

@Component
public class EventBus implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {
    private static int nThreads = 4;
    private static ApplicationContext context;
    private static AsyncEventBus DEFAULT = new AsyncEventBus(Executors.newFixedThreadPool(nThreads, new EventThreadFactory()));

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Map<String, IEventBusHandler> maps = context.getBeansOfType(IEventBusHandler.class);
        for(IEventBusHandler eventHandler : maps.values()){
            DEFAULT.register(eventHandler);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T extends AbstractEvent> void post(T event){
        DEFAULT.post(event);
    }
}
