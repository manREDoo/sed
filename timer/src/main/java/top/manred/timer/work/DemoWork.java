package top.manred.timer.work;

import top.manred.timer.GameWork;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 定义工作
 * </p>
 *
 * @author ： 钟满红
 * @date ： 2020/11/26 20:48
 */
public class DemoWork implements GameWork {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void run() {
        System.out.println("当前时间：" + LocalDateTime.now().format(DATETIME_FORMATTER));
    }
}
