/**
 * @title Loader.java
 * @package com.teligen.example.utils
 * @projectName example
 * @author yuzj
 * @date 2016年12月15日 上午10:06:29
 */
package com.shrimp.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;

/**
 * @title Loader.java
 * @projectName example
 * @author yuzj
 * @date 2016年12月15日 上午10:06:29
 */
public class Loader {
	private static String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 获取文件的url
	 * 
	 * @param resource
	 * @return
	 */
	public static URL getResource(String resource) {
		ClassLoader classLoader = null;
		URL url = null;
		try {
			classLoader = getClassLoader();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		url = classLoader.getResource(resource);
		return url;
	}

	public static InputStream getResourceAsStream(String name) {
		ClassLoader classLoader = null;
		InputStream is = null;
		try {
			classLoader = getClassLoader();
			is = classLoader.getResourceAsStream(name);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return is;

	}

	/**
	 * 获取当前thread的classloader
	 * 
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static ClassLoader getClassLoader() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Method method = null;
		try {
			method = Thread.class.getMethod("getContextClassLoader", null);
		} catch (NoSuchMethodException e) {
			return null;
		} catch (SecurityException e) {
			return null;
		}
		return (ClassLoader) method.invoke(Thread.currentThread(), null);
	}

	public static void fillProperties(Properties props, String name) {
		InputStream in = getResourceAsStream(name);
		if (props == null) {
			return;
		}
		try {
			props.load(new InputStreamReader(in, DEFAULT_ENCODING));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fillProperties(Properties props, String name, String encoding) {
		InputStream in = getResourceAsStream(name);
		if (props == null) {
			return;
		}
		try {
			props.load(new InputStreamReader(in, encoding));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fillProperties(Properties props, File file) {
		InputStream in = null;
		if (props == null || file == null) {
			return;
		}
		try {
			in = new FileInputStream(file);
			props.load(new InputStreamReader(in, DEFAULT_ENCODING));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fillProperties(Properties props, File file, String encoding) {
		InputStream in = null;
		if (props == null || file == null) {
			return;
		}
		try {
			in = new FileInputStream(file);
			props.load(new InputStreamReader(in, encoding));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Properties props = new Properties();
		Loader.fillProperties(props, "conf/syndata/resources.txt");
		System.out.println(props.get("jdbc.driverClassName"));
	}
}
