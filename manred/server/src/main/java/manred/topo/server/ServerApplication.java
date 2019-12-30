package manred.topo.server;

import manred.topo.server.framework.loadable.IAssemble;
import manred.topo.server.framework.loadable.ILoad;
import manred.topo.server.framework.net.NetServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@SpringBootApplication

public class ServerApplication implements CommandLineRunner {
	@Autowired
	private ApplicationContext context;

	@Autowired
	private NetServer netServer;

	private static long begin,end;

	public static void main(String[] args) {
		begin = System.currentTimeMillis();
		System.out.println("服务器启动");
		SpringApplication.run(ServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		start();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> stopAndDestroy()));
		end = System.currentTimeMillis();
		System.out.println("系统启动时间(秒)：" + (end - begin) / 1000);
	}

	private void start() {
		loadAllConfig();
		netServer.start();
	}

	private void stopAndDestroy() {
		try{
			stop();
			destroy();
			System.out.println("服务器关闭");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void stop() {

	}

	private void destroy() {

	}

	/**
	 * 加载所有配置
	 */
	private void loadAllConfig() {
		// 加载数据（加载Config开头的静态的数据）
		Map<String, ILoad> loadables = context.getBeansOfType(ILoad.class);
		if (loadables != null) {
			for (Map.Entry<String, ILoad> entry : loadables.entrySet()) {
				try {
					entry.getValue().load();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// 装配数据，这个是等所有静态数据都加载完成后才处理 （加载玩家数据，如排行榜等，）
		// 最好有个先后顺序，先加载哪一个，再加载哪个，避免有依赖关系，这个可以弄成异步加载，减少服务启动时间(by yu.c)
		Map<String, IAssemble> assembles = context.getBeansOfType(IAssemble.class);
		if (assembles != null) {
			for (Map.Entry<String, IAssemble> entry : assembles.entrySet()) {
				try {
					entry.getValue().assemble();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}
