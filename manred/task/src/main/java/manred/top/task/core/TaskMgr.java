package manred.top.task.core;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 任务管理
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/7 14:41
 */
@Component
public class TaskMgr implements ApplicationContextAware, InitializingBean {
    private ApplicationContext context;
    private List<TaskTrigger> taskList = new ArrayList<>();
    private Scheduler scheduler;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化调度器
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        //获取所有任务
        Map<String, ITask> tasks = context.getBeansOfType(ITask.class);
        for (ITask task : tasks.values()) {
            Method[] methods = task.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(CronTask.class)) {
                    CronTask cronTask = method.getDeclaredAnnotation(CronTask.class);
                    MethodInvoke methodInvoke = new MethodInvoke(task, method);
                    taskList.add(TaskUtil.getCronTask(cronTask.value(), methodInvoke));
                }else if (method.isAnnotationPresent(IntervalTask.class)){
                    IntervalTask intervalTask = method.getDeclaredAnnotation(IntervalTask.class);
                    MethodInvoke methodInvoke = new MethodInvoke(task, method);
                    taskList.add(TaskUtil.getIntervalTask(intervalTask.value(), intervalTask.delay(), methodInvoke));
                }
            }
        }

        //任务注册
        for (TaskTrigger taskTrigger : taskList) {
            scheduleJob(taskTrigger);
        }

        //开始任务
        start();
    }

    private void scheduleJob(TaskTrigger taskTrigger){
        try {
            scheduler.scheduleJob(taskTrigger.getJob(), taskTrigger.getTrigger());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 结束任务
     * @throws SchedulerException
     */
    public void shutdown() throws SchedulerException {
        scheduler.shutdown();
    }

    public void start(){
        //开始任务
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
