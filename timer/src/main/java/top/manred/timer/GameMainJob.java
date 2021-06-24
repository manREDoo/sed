package top.manred.timer;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 定时作业
 * </p>
 *
 * @author ： 钟满红
 * @date ： 2020/11/26 19:45
 */
public class GameMainJob implements Job {
    private static Logger logger = LoggerFactory.getLogger(GameMainJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        for (GameWork work : GameWorker.works(context.getTrigger().getKey())) {
            try {
                work.run();
            } catch (Exception e) {
                logger.error("定时器报错" + work.getClass(), e);
            }
        }
    }
}
