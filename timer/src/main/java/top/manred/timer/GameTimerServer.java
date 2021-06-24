package top.manred.timer;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 定时服务
 * </p>
 *
 * @author ： 钟满红
 * @date ： 2020/11/26 11:34
 */
public class GameTimerServer {
    private static Logger logger = LoggerFactory.getLogger(GameMainJob.class);
    private Scheduler scheduler = null;

    /**
     * 开启定时器
     */
    public void start() {
        try {
            //调度器工厂
            StdSchedulerFactory factory = new StdSchedulerFactory();
            //调度器
            scheduler = factory.getScheduler();
            for (GameTrigger t : GameTrigger.values()) {
                JobDetail jobDetail = JobBuilder.newJob(GameMainJob.class).withIdentity(t.name(), "gameJob").build();
                //注册定时工作
                scheduler.scheduleJob(jobDetail, t.getTrigger());
            }
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("定时器启动：", e);
        }
    }

    /**
     * 停止定时器
     */
    public void stop() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            logger.error("定时器结束：", e);
        }
    }
}
