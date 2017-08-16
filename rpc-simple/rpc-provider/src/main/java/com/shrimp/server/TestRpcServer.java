/**
 * @title TestRpcServer.java
 * @package com.smzh.server
 * @projectName rpc-provider
 * @author yuzj
 * @date 2017年8月15日 下午6:42:48
 */
package com.shrimp.server;

import java.io.IOException;

import com.shrimp.api.HelloService;
import com.shrimp.provider.HelloServiceImpl;

/**
 * @author yuzj
 */
public class TestRpcServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		ServerCenter center=new ServerCenter(8888);
		center.register(HelloService.class.getName(), HelloServiceImpl.class);
		center.start();
	}
}
