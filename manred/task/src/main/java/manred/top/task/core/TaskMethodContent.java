package manred.top.task.core;

import org.quartz.Trigger;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  映射时间和任务的容器
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/7 15:11
 */
public class TaskMethodContent {

    private static final Map<Trigger, MethodInvoke> triggerMap = new HashMap<>();

    public static void bindTriggerWithMethod(Trigger trigger, MethodInvoke methodInvoke){
        triggerMap.put(trigger, methodInvoke);
    }

    public static MethodInvoke get(Trigger trigger){
        return triggerMap.get(trigger);
    }
}
