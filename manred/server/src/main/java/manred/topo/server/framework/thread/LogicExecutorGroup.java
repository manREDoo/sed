package manred.topo.server.framework.thread;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutor;

public class LogicExecutorGroup {
	public static final DefaultEventExecutorGroup executors = new DefaultEventExecutorGroup(
			Runtime.getRuntime().availableProcessors() * 2, new CoreThreadFactory("gameLogic"));

	public static EventExecutor nextExecutor(){
		EventExecutor ex;
		do {
			ex = executors.next();
		} while (ex.isShutdown());
		return ex;
	}
}
