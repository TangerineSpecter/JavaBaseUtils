package com.tangerineSpecter.javaBaseUtils.common.util;

import common.annotation.ClassInfo;

/**
 * 参数常量类
 * 
 * @author TangerineSpecter
 */
@ClassInfo(Name = "参数常量类")
public class Constant {
	
	/**
	 * 版本号
	 */
	public static final String VERSION = "2.0.0";

	/**
	 * 文件默认存放地址
	 */
	public static final String FILE_SAVE_PATH = "F:\\testFile\\";
	/**
	 * 压缩文件存放地址
	 */
	public static final String ZIP_SAVE_PATH = "F:\\testFile\\zip\\";

	/**
	 * 空定义
	 */
	public static final String NULL_KEY_STR = "";

	/**
	 * 工具类全限定名头
	 */
	public static final String UTIL_QUALIFIED_HEAD = "common.util.";

	/**
	 * 工具类绝对路径
	 */
	public static final String UTIL_ABSOLUTE_PATH = System.getProperty("user.dir") + "/src/common/util";

	/**
	 * 日期常量
	 */
	public static class Date {
		/** 一月 */
		public static final int MONTH_JANUARY = 1;
		/** 二月 */
		public static final int MONTH_FEBRUARY = 2;
		/** 三月 */
		public static final int MONTH_MARCH = 3;
		/** 四月 */
		public static final int MONTH_APRIL = 4;
		/** 五月 */
		public static final int MONTH_MAY = 5;
		/** 六月 */
		public static final int MONTH_JUNE = 6;
		/** 七月 */
		public static final int MONTH_JULY = 7;
		/** 八月 */
		public static final int MONTH_AUGUST = 8;
		/** 九月 */
		public static final int MONTH_SEPTEMBER = 9;
		/** 十月 */
		public static final int MONTH_OCTOBER = 10;
		/** 十一月 */
		public static final int MONTH_NOVEMBER = 11;
		/** 十二月 */
		public static final int MONTH_DECEMBER = 12;
		/** 润二月 */
		public static final int LEAP_YEAR_DAY = 29;
		/** 一秒相对的毫秒数 */
		public static final Long ONE_SECOND = 1000L;
		/** 一分钟相对的毫秒数 */
		public static final Long ONE_MINUTE = 60L * ONE_SECOND;
		/** 一小时相对的毫秒数 */
		public static final Long ONE_HOUR = 60L * ONE_MINUTE;
		/** 一天相对的毫秒数 */
		public static final Long ONE_DAY = 24L * ONE_HOUR;
		/** 一分钟相对的秒数 */
		public static final int ONE_MINUTE_OF_SECONDS = 60;
		/** 一小时相对的秒数 */
		public static final int ONE_HOUR_OF_SECONDS = 60 * ONE_MINUTE_OF_SECONDS;
		/** 一天相对的秒数 */
		public static final int ONE_DAY_OF_SECONDS = 24 * ONE_HOUR_OF_SECONDS;
		/** 一个月相对的秒数 */
		public static final int ONE_MONTH_OF_SECONDS = 30 * ONE_DAY_OF_SECONDS;
		/** 一年相对的秒数 */
		public static final int ONE_YEAR_OF_SECONDS = 12 * ONE_MONTH_OF_SECONDS;
	}

	/**
	 * 基本常量
	 */
	public static class Number {
		/** 数字0 */
		public static final Integer COMMON_NUMBER_ZERO = 0;
		/** 数字1 */
		public static final Integer COMMON_NUMBER_FIRST = 1;
		/** 数字2 */
		public static final Integer COMMON_NUMBER_SECOND = 2;
		/** 数字3 */
		public static final Integer COMMON_NUMBER_THIRD = 3;
	}

	/**
	 * 加密类型
	 */
	public static class Deciphering {
		/** 摩斯密码 */
		public static final String MORSE_TYPE = "摩斯密码";
		/** 栅栏密码 */
		public static final String RAILFENCE_TYPE = "栅栏密码";
		/** 手机九宫格 */
		public static final String PHONE_TYPEWRITING_TYPE = "手机九宫格";
		/** 键盘密码 */
		public static final String KEYBOARD_TYPE = "键盘密码";
		/** 培根密码 */
		public static final String BACON_TYPE = "培根密码";
		/** 倒序密码 */
		public static final String REVERSE_ORDER_TYPE = "倒序密码";

		/** 摩斯密码索引 */
		public static final int INDEX_MORSE = 0;
		/** 栅栏密码索引 */
		public static final int INDEX_RAILFENCE = 1;
		/** 手机九宫格索引 */
		public static final int INDEX_PHONE_TYPEWRITING = 2;
		/** 键盘密码索引 */
		public static final int INDEX_KEYBOARD_TYPE = 3;
		/** 培根密码索引 */
		public static final int INDEX_BACON = 4;
		/** 倒序密码索引 */
		public static final int INDEX_REVERSE_ORDER = 5;
	}

	public static class Chinese {
		/**
		 * 无字符串定义
		 */
		public static final String NOTHING = "无";

		/**
		 * 工具类
		 */
		public static final String TOOL = "工具类";

		/**
		 * 匿名
		 */
		public static final String ANONYMOUS = "匿名";
	}

	/** reids缓存相关配置 **/
	public static class Redis {
		public static String REDIS_IP = "127.0.0.1";
		public static String REDIS_PASSWD = null;
		public static int REIDS_MAX_ACTIVE = 100;
		public static int REIDS_MAX_IDLE = 50;
		public static int REIDS_MIN_IDLE = 20;
		public static int REIDS_MAX_WAITTIME = 2000;
	}
	
	/**
	 * github-blob
	 */
	public static String GIT_HUB_BLOB_URL = "https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/";

}
