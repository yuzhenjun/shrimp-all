/**
 * @title Prop.java
 * @package com.teligen.example.utils
 * @projectName example
 * @author yuzj
 * @date 2016年12月19日 下午5:46:58
 */
package com.shrimp.common.utils;

import java.io.File;
import java.util.Properties;

/**
 * @title Prop.java
 * @projectName example
 * @author yuzj
 * @date 2016年12月19日 下午5:46:58
 */
public class Prop {
	private Properties properties = null;
	/**
	 * fileName文件相对路径
	 */
	public Prop(String fileName){
		this(fileName,ConstantsUtils.DEFAULT_ENCODING);
	}
	
	public Prop(File file){
		this(file,ConstantsUtils.DEFAULT_ENCODING);
	}
	
	public Prop(String fileName, String encoding){
		properties=new Properties();
		Loader.fillProperties(properties, fileName,encoding);
	}
	
	public Prop(File file, String encoding){
		properties=new Properties();
		Loader.fillProperties(properties, file,encoding);
	}
	
	public String get(String key) {
		return properties.getProperty(key);
	}
	
	public String get(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
	
	public Integer getInt(String key) {
		return getInt(key, null);
	}
	
	public Integer getInt(String key, Integer defaultValue) {
		String value = properties.getProperty(key);
		if (value != null)
			return Integer.parseInt(value.trim());
		return defaultValue;
	}
	
	public Long getLong(String key) {
		return getLong(key, null);
	}
	
	public Long getLong(String key, Long defaultValue) {
		String value = properties.getProperty(key);
		if (value != null)
			return Long.parseLong(value.trim());
		return defaultValue;
	}
	
	public Boolean getBoolean(String key) {
		return getBoolean(key, null);
	}
	
	public Boolean getBoolean(String key, Boolean defaultValue) {
		String value = properties.getProperty(key);
		if (value != null) {
			value = value.toLowerCase().trim();
			if ("true".equals(value))
				return true;
			else if ("false".equals(value))
				return false;
			throw new RuntimeException("The value can not parse to Boolean : " + value);
		}
		return defaultValue;
	}
	
	public boolean containsKey(String key) {
		return properties.containsKey(key);
	}
	
	public Properties getProperties() {
		return properties;
	}
}
