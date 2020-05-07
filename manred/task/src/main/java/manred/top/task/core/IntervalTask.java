package manred.top.task.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 保存时间间隔
 * </p>
 *
 * @author ： 钟满红
 * @createtime ： 2020/5/7 15:18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IntervalTask {
    //时间间隔
    long value() default 0;
    //延时处理
    long delay() default 0;
}
