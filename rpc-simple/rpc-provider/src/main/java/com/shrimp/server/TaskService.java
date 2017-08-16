/**
 * @title TaskService.java
 * @package com.smzh.server
 * @projectName rpc-provider
 * @author yuzj
 * @date 2017年8月15日 下午3:35:37
 */
package com.shrimp.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author yuzj
 */
public class TaskService implements Runnable {

	public Socket server;
	private Map<String, Class<?>> serviceRegister;

	public TaskService(Socket server, Map<String, Class<?>> serviceRegister) {
		this.server = server;
		this.serviceRegister = serviceRegister;
	}

	public void run() {
		ObjectInputStream input = null;
		ObjectOutputStream output = null;
		try {
			input = new ObjectInputStream(server.getInputStream());
			// 获取bean名称
			String bean = input.readUTF();
			// 方法名称
			String methodName = input.readUTF();
			// 获取参数类型
			Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
			// 获取参数
			Object[] arguments = (Object[]) input.readObject();
			// 获取服务实现类
			Class<?> serviceClass = serviceRegister.get(bean);
			if (serviceClass == null) {
				throw new ClassNotFoundException(bean + " not found");
			}

			Method method = serviceClass.getMethod(methodName, parameterTypes);
			//反射机制
			Object object = method.invoke(serviceClass.newInstance(), arguments);
			output = new ObjectOutputStream(server.getOutputStream());
			output.writeObject(object);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
