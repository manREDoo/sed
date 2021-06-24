package manred.top.task.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *  保存cron表达式
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/7 14:51
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CronTask {
    String value() default "";
}
