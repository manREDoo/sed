package manred.top.task.core;

import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * <p>
 * 任务和时间
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/7 14:35
 */
public class TaskTrigger {
    private JobDetail job;
    private Trigger trigger;

    public TaskTrigger(JobDetail job, Trigger trigger) {
        this.job = job;
        this.trigger = trigger;
    }

    public JobDetail getJob() {
        return job;
    }

    public void setJob(JobDetail job) {
        this.job = job;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
}
