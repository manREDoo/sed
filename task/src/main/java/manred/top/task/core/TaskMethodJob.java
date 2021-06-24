package manred.top.task.core;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>
 * 任务执行操作
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/7 14:58
 */
public class TaskMethodJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        MethodInvoke methodInvoke = TaskMethodContent.get(jobExecutionContext.getTrigger());
        if (methodInvoke != null){
            methodInvoke.invoke();
        }
    }
}
