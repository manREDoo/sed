package manred.top.task.my;

import manred.top.task.core.CronTask;
import manred.top.task.core.ITask;
import manred.top.task.core.IntervalTask;
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
public class MyTask implements ITask {

    @IntervalTask(value = 1000)
    public void Hello(){
        System.out.println("Hello manred!");
    }

    @CronTask("* * * * * ?")
    public void Hi(){
        System.out.println("Hi manred!");
    }
}
