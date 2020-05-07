package manred.top.task.spring;

import manred.top.task.core.CronTask;
import manred.top.task.core.ITask;
import manred.top.task.core.IntervalTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 测试
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/7 15:39
 */
@Component
public class SpringTask {

    @Scheduled(cron = "* * * * * ?")
    public void Hi(){
        System.out.println("Hi spring!");
    }
}
