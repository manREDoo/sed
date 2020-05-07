package manred.top.task.core;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import java.util.Date;

/**
 * <p>
 * 任务工具类
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/7 14:36
 */
public class TaskUtil {

    /**
     * cron表达式构建任务
     * @param cron
     * @param methodInvoke
     * @return
     */
    public static TaskTrigger getCronTask(String cron, MethodInvoke methodInvoke) {
        JobDetail job = JobBuilder.newJob(TaskMethodJob.class)
                .withIdentity(methodInvoke.getMethod().getName(),methodInvoke.getObj().getClass().getName())
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(methodInvoke.getMethod().getName(),methodInvoke.getObj().getClass().getName())
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
        TaskMethodContent.bindTriggerWithMethod(trigger, methodInvoke);
        return new TaskTrigger(job, trigger);
    }

    /**
     * 时间间隔构建任务
     * @param interval 毫秒
     * @param delay 毫秒
     * @param methodInvoke
     * @return
     */
    public static TaskTrigger getIntervalTask(long interval, long delay, MethodInvoke methodInvoke) {
        JobDetail job = JobBuilder.newJob(TaskMethodJob.class).withIdentity(methodInvoke.getMethod().getName(), methodInvoke.getObj().getClass().getName()).build();
        Trigger trigger;
        if (delay > 0){
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(methodInvoke.getMethod().getName(), methodInvoke.getObj().getClass().getName())
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(interval).repeatForever())
                    .startAt(new Date(System.currentTimeMillis() + delay))
                    .build();
        }else {
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(methodInvoke.getMethod().getName(), methodInvoke.getObj().getClass().getName())
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(interval).repeatForever())
                    .startNow().build();
        }
        TaskMethodContent.bindTriggerWithMethod(trigger, methodInvoke);
        return new TaskTrigger(job, trigger);
    }
}
