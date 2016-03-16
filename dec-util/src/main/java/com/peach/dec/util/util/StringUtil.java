package com.peach.dec.util.util;

/**
 * 字符串工具类
 * 
 * @author Administrator
 * 
 */
public class StringUtil {

	/**
	 * 判断字符串等于空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str);
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

}
