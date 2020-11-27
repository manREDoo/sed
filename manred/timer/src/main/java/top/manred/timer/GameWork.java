package top.manred.timer;

/**
 * <p>
 * 工作
 * </p>
 *
 * @author ： 钟满红
 * @date ： 2020/11/26 20:36
 */
public interface GameWork extends Runnable {
    /**
     * 工作内容
     */
    @Override
    default void run() {

    }
}
