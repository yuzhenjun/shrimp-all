/**
 * @title HelloService.java
 * @package com.smzh.api
 * @projectName rpc-api
 * @author yuzj
 * @date 2017年8月15日 上午11:31:23
 */
package com.shrimp.api;

import java.util.List;

/**
 * @author yuzj
 */
public interface HelloService {

	String sayHello(String name);

	List<String> getList();

}
