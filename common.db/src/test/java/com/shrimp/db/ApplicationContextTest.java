/**
 * @title ApplicationContextTest.java
 * @package com.shrimp.db
 * @projectName common.db
 * @author yuzj
 * @date 2017年2月7日 下午9:58:53
 */
package com.shrimp.db;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yuzj
 */
public class ApplicationContextTest {

	@Test
	public void testClassPathXML() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application-db.xml");
		context.start();
		
	}
}
