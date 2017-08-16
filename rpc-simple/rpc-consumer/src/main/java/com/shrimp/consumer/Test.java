package com.shrimp.consumer;

import java.net.InetSocketAddress;

import com.shrimp.api.HelloService;

public class Test {

	public static void main(String[] args) {
		HelloService service = RPCClient.getRemoteProxy(HelloService.class, new InetSocketAddress("localhost", 8888));
        System.out.println(service.sayHello("test"));
        System.out.println(service.getList());
	}
}
