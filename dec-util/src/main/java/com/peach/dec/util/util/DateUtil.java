package com.peach.dec.util.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 时间工具类
 * 
 * @author peach
 * @date 2015年3月10日
 * @version V1.0
 */
public class DateUtil {
	private static Logger log = Logger.getLogger(DateUtil.class);

	/**
	 * 把时间按照规定的格式转化为字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
		return sDateFormat.format(date);
	}

	/**
	 * 给定字符串的开始解析文本，以生成一个日期
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static Date parse(String source, String pattern) {
		Date date = null;
		SimpleDateFormat simFormat = new SimpleDateFormat(pattern);
		try {
			date = simFormat.parse(source);
		} catch (ParseException e) {
			log.error("字符串转换成时间失败" + e.getMessage());
			e.printStackTrace();
		}
		return date;

	}

	/**
	 * java.sql.Date -> java.util.Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date sqlDateCastUtilDate(java.sql.Date date) {
		return new Date(date.getTime());
	}

	/**
	 * java.util.Date -> java.sql.Date
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date sqlDateCastUtilDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long compare(Date date1, Date date2) {
		long firstDate = date1.getTime();
		long secondDate = date2.getTime();
		if (firstDate > secondDate) {
			return firstDate - secondDate;
		} else {
			return secondDate - firstDate;
		}

	}

}
