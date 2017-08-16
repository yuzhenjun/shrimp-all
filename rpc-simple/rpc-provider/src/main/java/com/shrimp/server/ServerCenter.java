/**
 * @title ServerCenter.java
 * @package com.smzh.server
 * @projectName rpc-provider
 * @author yuzj
 * @date 2017年8月15日 上午11:50:40
 */
package com.shrimp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yuzj
 */
public class ServerCenter implements Server {

	/**
	 * 注册服务类
	 */
	private Map<String, Class<?>> serviceRegister = new HashMap<String, Class<?>>();

	private boolean isRunning = false;

	private int port;

	private ServerSocket server;

	/**
	 * 线程池
	 */
	private ExecutorService executor = Executors.newFixedThreadPool(Runtime
			.getRuntime().availableProcessors());

	public ServerCenter(int port) {
		this.port = port;
	}

	public void stop() {
		if(!executor.isShutdown()){
			executor.shutdown();
		}
		if(server!=null){
			try {
				server.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		isRunning = false;
	}

	public int getPort() {
		return this.port;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void register(String bean, Class<?> impl) {
		serviceRegister.put(bean, impl);
	}

	public void start() throws IOException {
		server = new ServerSocket(getPort());
		this.isRunning = true;
		System.out.println("rpc 服务启动成功,监听端口为"+getPort());
		try {
			while (true) {
				// 启动TaskService任务，当接收到客户端请求是进行处理
				executor.execute(new TaskService(server.accept(),serviceRegister));
			}
		} finally {
			this.isRunning = false;
			executor.shutdown();
			server.close();
		}

	}

}
