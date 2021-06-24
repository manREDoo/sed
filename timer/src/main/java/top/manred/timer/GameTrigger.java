package top.manred.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

/**
 * <p>
 * 游戏定时器时间表
 * </p>
 *
 * @author ： 钟满红
 * @date ： 2020/11/26 19:52
 */
public enum GameTrigger {
    /**
     * 格式: [秒] [分] [小时] [日] [月] [周] [年]
     * * 表示所有值. 例如:在分的字段上设置 "*",表示每一分钟都会触发。
     * ?  表示不指定值。使用的场景为不需要关心当前设置这个字段的值。例如:要在每月的10号触发一个操作，但不关心是周几，所以需要周位置的那个字段设置为"?" 具体设置为 0 0 0 10 *  ?
     * -  表示区间。例如 在小时上设置 "10-12",表示 10,11,12点都会触发。
     * ,  表示指定多个值，例如在周字段上设置 "MON,WED,FRI" 表示周一，周三和周五触发
     * / 用于递增触发。如在秒上面设置"5/15" 表示从5秒开始，每增15秒触发(5,20,35,50)。 在月字段上设置'1/3'所示每月1号开始，每隔三天触发一次。
     * 例: 0 0 0 * * ?	每天0点触发
     */

    /**
     * 每秒
     */
    SECOND(TriggerBuilder.newTrigger()
            .withIdentity("second", "gameTrigger")
            .withPriority(100)
            .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
            .build()),
    /**
     * 每分
     */
    MINUTE(TriggerBuilder.newTrigger()
            .withIdentity("minute", "gameTrigger")
            .withPriority(90)
            .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ?"))
            .build()),
    /**
     * 每小时
     */
    HOUR(TriggerBuilder.newTrigger()
            .withIdentity("hour", "gameTrigger")
            .withPriority(80)
            .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * * * ?"))
            .build()),
    /**
     * 每天0点
     */
    DAY(TriggerBuilder.newTrigger()
            .withIdentity("day", "gameTrigger")
            .withPriority(70)
            .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 * * ?"))
            .build()),
    ;

    private final Trigger trigger;

    GameTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public Trigger getTrigger() {
        return trigger;
    }
}
