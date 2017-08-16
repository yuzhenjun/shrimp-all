/**
 * @title Server.java
 * @package com.smzh.server
 * @projectName rpc-provider
 * @author yuzj
 * @date 2017年8月15日 上午11:43:23
 */
package com.shrimp.server;

import java.io.IOException;

/**
 * @author yuzj
 */
public interface Server {
	/**
	 * 启动服务
	 */
	public void start()  throws IOException;

	/**
	 * 关闭服务
	 */
	public void stop();

	/**
	 * 获取监听端口号
	 * 
	 * @return
	 */
	public int getPort();

	/**
	 * 返回应用运行情况<br>
	 * true :运行      <br>
	 * false:关闭      <br>
	 */
	public boolean isRunning();
	/**
	 * 注册服务
	 * @param bean
	 * @param impl
	 */
	public void register(String bean,Class<?> impl);
}
