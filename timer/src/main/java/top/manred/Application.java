package top.manred;

import top.manred.timer.GameTimerServer;

/**
 * <p>
 *
 * </p>
 *
 * @author ： 钟满红
 * @date ： 2020/11/27 10:43
 */
public class Application {
    public static void main(String[] args) {
        GameTimerServer gameTimerServer = new GameTimerServer();
        gameTimerServer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            gameTimerServer.start();
        }));
    }
}
