package top.manred.timer;

import org.quartz.TriggerKey;
import top.manred.timer.work.DemoWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 按时工作实例对象
 * </p>
 *
 * @author ： 钟满红
 * @date ： 2020/11/26 20:50
 */
public enum GameWorker {
    /**
     * 示例
     */
    DEMO_SECOND(GameTrigger.SECOND, new DemoWork()),
    /**
     * 示例
     */
//    DEMO_MINUTE(GameTrigger.MINUTE, new DemoWork()),
    /**
     * 示例
     */
//    DEMO_HOUR(GameTrigger.HOUR, new DemoWork()),
    /**
     * 示例
     */
//    DEMO_DAY(GameTrigger.DAY, new DemoWork()),

    ;
    /**
     * 工作时间
     */
    private final GameTrigger gameTrigger;
    /**
     * 工作内容
     */
    private final GameWork gameWork;

    GameWorker(GameTrigger gameTrigger, GameWork gameWork) {
        this.gameWork = gameWork;
        this.gameTrigger = gameTrigger;
    }

    private static final Map<TriggerKey, GameWork[]> CACHE = new HashMap<>();

    static {
        Map<TriggerKey, List<GameWork>> temp = new HashMap<>();
        for (GameWorker value : values()) {
            TriggerKey key = value.gameTrigger.getTrigger().getKey();
            temp.putIfAbsent(key, new ArrayList<>());
            temp.get(key).add(value.gameWork);
        }
        System.out.println(temp);
        temp.forEach((k, v) -> {
            CACHE.putIfAbsent(k, v.toArray(new GameWork[0]));
        });
    }

    /**
     * 同一时间的工作
     *
     * @param key 工作时间的Key
     * @return 多个工作
     */
    public static GameWork[] works(TriggerKey key) {
        return CACHE.getOrDefault(key, new GameWork[0]);
    }
}
