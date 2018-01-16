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
	public static String FILE_SAVE_PATH = "F:\\testFile\\";

	/**
	 * 空定义
	 */
	public static String NULL_KEY_STR = "";

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
		public static final long ONE_SECOND = 1000;
		/** 一分钟相对的毫秒数 */
		public static final long ONE_MINUTE = 60 * ONE_SECOND;
		/** 一小时相对的毫秒数 */
		public static final long ONE_HOUR = 60 * ONE_MINUTE;
		/** 一天相对的毫秒数 */
		public static final long ONE_DAY = 24 * ONE_HOUR;
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
}
