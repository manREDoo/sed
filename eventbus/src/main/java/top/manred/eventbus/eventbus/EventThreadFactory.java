package top.manred.eventbus.eventbus;

import java.util.concurrent.ThreadFactory;

public class EventThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("eventThread");
        return thread;
    }
}
