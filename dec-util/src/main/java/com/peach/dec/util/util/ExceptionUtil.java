package com.peach.dec.util.util;

import org.apache.log4j.Logger;

/**
 * 异常工具类
 * 
 * @author peach。
 * @date 2015-4-2
 */
public class ExceptionUtil {

	public static void error(Object obj, String message, Exception e) {
		Logger log = Logger.getLogger(obj.getClass());
		log.error(message + "失败：" + e.getMessage(), e);
	}

}
