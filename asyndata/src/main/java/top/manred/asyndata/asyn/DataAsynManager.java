package top.manred.asyndata.asyn;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DataAsynManager implements IDataAsynManager, ApplicationContextAware {
    private static final ConcurrentLinkedQueue<AsyncObject> asyncQueue = new ConcurrentLinkedQueue<>();
    private static final int step = 10;//步长，每次移除的个数
    private static final int speed = 2000;

    private ApplicationContext context;
    private ExecutorService executor;
    private Thread syncThread;
    private boolean serverStopped = false;

    public DataAsynManager() {
        executor = Executors.newFixedThreadPool(step, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread tr = new Thread(r);
                tr.setName("asyncdbtr" + tr.getId());
                return tr;
            }
        });

        syncThread = new Thread(new SyncController(), "asyncdbthread");
        syncThread.start();
    }

    /**
     * 同步控制器 控制同步的频率，以此来定制对DB的压力
     */
    private class SyncController implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    int rate = 1000 / (speed / step);
                    for (int i = 0; i < step && !asyncQueue.isEmpty(); i++) {
                        final AsyncObject data = asyncQueue.remove();
                        if (data != null) {
                            executor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        //入库操作
                                        data.setSyncStatus(AsyncObject.SYNC_SAVING, AsyncObject.SYNC_COMMITED);
                                        synchronous(data);
                                        data.setSyncStatus(AsyncObject.SYNC_UNCOMMIT, AsyncObject.SYNC_SAVING);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                    //线程挂起
                    if (!serverStopped) {
                        try {
                            Thread.sleep(rate);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {//关闭整个线程池
                        executor.shutdown();
                        while (executor.isTerminated()) {
                            if (!asyncQueue.isEmpty()) {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 处理入库数据
     */
    private void synchronous(AsyncObject data) {
        try {
            Class<?> clazz = data.getClass().getAnnotation(Provider.class).value();
            PersistentProvider provider = (PersistentProvider) context.getBean(clazz);//真正入库的操作类
            if (data.getOpt() == DbOperate.INSERT) {
                provider.save(data);
            } else if (data.getOpt() == DbOperate.UPDATE) {
                provider.update(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void saveAsync(AsyncObject data) {
        commitSync(data, DbOperate.INSERT);
    }

    @Override
    public void updateAsync(AsyncObject data) {
        commitSync(data, DbOperate.UPDATE);
    }

    /**
     * 将数据放入同步队列
     *
     * @param data
     * @param opt
     */
    private void commitSync(AsyncObject data, DbOperate opt) {
        System.out.println("数据加入异步队列：" + data.toString());
        if (data.setSyncStatus(AsyncObject.SYNC_COMMITED, AsyncObject.SYNC_UNCOMMIT)) {
            data.setOpt(opt);
            asyncQueue.add(data);
        }
    }
}
