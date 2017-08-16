/**
 * @title RPCClient.java
 * @package comsmzh.comsumer
 * @projectName rpc-comsumer
 * @author yuzj
 * @date 2017年8月15日 下午3:54:30
 */
package com.shrimp.consumer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author yuzj
 */
public class RPCClient<T> {

	/**
	 * 客户端通过动态代理，调用服务端代码
	 * @param interfaceClass
	 * @param addr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getRemoteProxy(final Class<?> interfaceClass, final InetSocketAddress addr) {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[] { interfaceClass }, 
				new InvocationHandler() {
					/**
					 * invoke是动态代理的核心，即在调用interfaceClass类中的方法时要执行的动作<br>
					 * 可以在调用方法之前执行一些动作，在调用方法之后执行一些动作 <br>
					 * 该rpc实现的关键是在调用方法的时候，通过动态代理模式，将类名，方法名，参数等一些参数通过socket套接字发送给服务端<br>
					 * 服务端接收到请求后根据相应参数，根据java反射机制调用服务端实现类，然后将结果返回给客户端<br>
					 */
					@SuppressWarnings("resource")
					public Object invoke(Object proxy, Method method,Object[] args) throws Throwable {
						ObjectInputStream input = null;
						ObjectOutputStream output = null;
						Socket socket = new Socket();
						// 连接服务
						socket.connect(addr);
						output = new ObjectOutputStream(socket.getOutputStream());
						//首先写入类名
						output.writeUTF(interfaceClass.getName());
						output.writeUTF(method.getName());
                        output.writeObject(method.getParameterTypes());
                        output.writeObject(args);
                         // 同步阻塞等待服务器返回应答，获取应答后返回
                        input = new ObjectInputStream(socket.getInputStream());
                        return input.readObject();
					}
				});
	}
}
