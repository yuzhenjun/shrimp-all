/**
 * @title HelloServiceImpl.java
 * @package com.smzh.provider
 * @projectName rpc-provider
 * @author yuzj
 * @date 2017年8月15日 上午11:41:05
 */
package com.shrimp.provider;

import java.util.ArrayList;
import java.util.List;

import com.shrimp.api.HelloService;

/**
 * @author yuzj
 */
public class HelloServiceImpl implements HelloService {

	public String sayHello(String name) {
		String str = "hello world ,my name is " + name;
		System.out.println(name);
		return str;
	}

	public List<String> getList() {
		List<String> result=new ArrayList<String>();
		for(int i=0;i<10;i++){
			result.add("aaaa"+i);
		}
		return result;
	}
}
