package common.util;

/**
 * 参数常量类
 * 
 * @author TangerineSpecter
 */
public class Constant {

	/**
	 * 文件存放地址
	 */
	public static final String FILE_SAVE_PATH = "F:\\testFile\\";

	/**
	 * 空定义
	 */
	public static final String NULL_KEY_STR = "";

	/**
	 * 日期常量
	 */
	public static class Date {
		/** 一月 */
		public static final Integer MONTH_JANUARY = 1;
		/** 二月 */
		public static final Integer MONTH_FEBRUARY = 2;
		/** 三月 */
		public static final Integer MONTH_MARCH = 3;
		/** 四月 */
		public static final Integer MONTH_APRIL = 4;
		/** 五月 */
		public static final Integer MONTH_MAY = 5;
		/** 六月 */
		public static final Integer MONTH_JUNE = 6;
		/** 七月 */
		public static final Integer MONTH_JULY = 7;
		/** 八月 */
		public static final Integer MONTH_AUGUST = 8;
		/** 九月 */
		public static final Integer MONTH_SEPTEMBER = 9;
		/** 十月 */
		public static final Integer MONTH_OCTOBER = 10;
		/** 十一月 */
		public static final Integer MONTH_NOVEMBER = 11;
		/** 十二月 */
		public static final Integer MONTH_December = 12;
		/** 润二月 */
		public static final Integer LEAP_YEAR_DAY = 29;
		/** 一秒相对的毫秒数 */
		public static final Long ONE_SECOND = 1000L;
		/** 一分钟相对的毫秒数 */
		public static final Long ONE_MINUTE = 60L * ONE_SECOND;
		/** 一小时相对的毫秒数 */
		public static final Long ONE_HOUR = 60L * ONE_MINUTE;
		/** 一天相对的毫秒数 */
		public static final Long ONE_DAY = 24L * ONE_HOUR;
		/** 一分钟相对的秒数 */
		public static final Integer ONE_MINUTE_OF_SECONDS = 60;
		/** 一小时相对的秒数 */
		public static final Integer ONE_HOUR_OF_SECONDS = 60 * ONE_MINUTE_OF_SECONDS;
		/** 一天相对的秒数 */
		public static final Integer ONE_DAY_OF_SECONDS = 24 * ONE_HOUR_OF_SECONDS;
		/** 一个月相对的秒数 */
		public static final Integer ONE_MONTH_OF_SECONDS = 30 * ONE_DAY_OF_SECONDS;
		/** 一年相对的秒数 */
		public static final Integer ONE_YEAR_OF_SECONDS = 12 * ONE_MONTH_OF_SECONDS;
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
}
