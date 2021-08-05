package com.tangerinespecter.javabaseutils.common.util;

/**
 * 基础工具类
 * 
 * @author TangerineSpecter
 *
 */
public class BaseUtils extends LoggerWordPool {

	/**
	 * 日志信息
	 */
	public static String loggerMessage(String info, Object param) {
		return String.format(info, param);
	}

}
