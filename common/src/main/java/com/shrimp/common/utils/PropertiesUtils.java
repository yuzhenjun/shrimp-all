/**
 * 配置文件公用类
 * @title PropertiesUtils.java
 * @package com.teligen.example.utils
 * @projectName example
 * @author yuzj
 * @date 2016年11月16日 下午5:11:34
 */
package com.shrimp.common.utils;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 属性文件加载工具
 * @title PropertiesUtils.java
 * @projectName example
 * @author yuzj
 * @date 2016年11月16日 下午5:11:34
 */
public class PropertiesUtils {

	private static final ConcurrentHashMap<String, Prop> map = new ConcurrentHashMap<String, Prop>();

	private static Prop prop = null;

	/**
	 * 根据文件相对路径获取文件，默认编码为UTF-8<br>
	 * 如果内存中不存在则去加载，并放入缓存map中
	 * @param fileName
	 * @return
	 */
	public static Prop use(String fileName) {
		return use(fileName, ConstantsUtils.DEFAULT_ENCODING);
	}

	public static Prop use(String fileName, String encoding) {
		Prop result = map.get(fileName);
		if (result == null) {
			result = new Prop(fileName, encoding);
			map.put(fileName, result);
			if (PropertiesUtils.prop == null) {
				PropertiesUtils.prop = result;
			}
		}
		return result;
	}

	public static Prop use(File file) {
		return use(file, ConstantsUtils.DEFAULT_ENCODING);
	}

	public static Prop use(File file, String encoding) {
		String key = file.getName();
		Prop result = map.get(key);
		if (result == null) {
			result = new Prop(file, encoding);
			map.put(key, result);
			if (PropertiesUtils.prop == null) {
				PropertiesUtils.prop = result;
			}
		}
		return result;
	}
	
	public static Prop getProp() {
		if (prop == null)
			throw new IllegalStateException("Load propties file by invoking PropertiesUtils.use(String fileName) method first.");
		return prop;
	}
	
	public static Prop getProp(String fileName) {
		return map.get(fileName);
	}
	
	public static String get(String key) {
		return getProp().get(key);
	}
	
	public static String get(String key, String defaultValue) {
		return getProp().get(key, defaultValue);
	}
	
	public static Integer getInt(String key) {
		return getProp().getInt(key);
	}
	
	public static Integer getInt(String key, Integer defaultValue) {
		return getProp().getInt(key, defaultValue);
	}
	
	public static Long getLong(String key) {
		return getProp().getLong(key);
	}
	
	public static Long getLong(String key, Long defaultValue) {
		return getProp().getLong(key, defaultValue);
	}
	
	public static Boolean getBoolean(String key) {
		return getProp().getBoolean(key);
	}
	
	public static Boolean getBoolean(String key, Boolean defaultValue) {
		return getProp().getBoolean(key, defaultValue);
	}
	
	public static boolean containsKey(String key) {
		return getProp().containsKey(key);
	}

	public static void main(String[] args)  {
		Prop prop1=PropertiesUtils.use("test/protocol.properties");
		System.out.println(prop1.get("PS_4G_ACTIVITY.index"));
	}

}
