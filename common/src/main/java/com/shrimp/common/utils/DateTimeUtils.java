/**
 * @title DateTimeUtils.java
 * @package com.teligen.hbase.utils
 * @projectName hbase.example
 * @author yuzj
 * @date 2017年6月1日 上午9:16:50
 */
package com.shrimp.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @title DateTimeUtils.java
 * @projectName hbase.example
 * @author yuzj
 * @date 2017年6月1日 上午9:16:50
 */
public class DateTimeUtils {
	public static Date dateFormat(String dateStr, String parten) {
		SimpleDateFormat format = new SimpleDateFormat(parten);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
		}

		return null;
	}

	public static String dateFormate(Date date, String parten) {
		SimpleDateFormat format = new SimpleDateFormat(parten);
		return format.format(date);
	}
}
