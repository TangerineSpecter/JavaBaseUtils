package common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import common.annotation.MethodInfo;

/**
 * 时间处理工具类
 * 
 * @author TangerineSpecter
 */
public class TimeUtils {
	private static Logger logger = Logger.getLogger(TimeUtils.class);
	/** 默认格式 */
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
	/** 时间格式——精确到秒 */
	private static final String DEFAULT_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
	/** 星期数 */
	private static final String[] WEEKDAYS = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 将时间转换成指定格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            时间
	 * @return
	 */
	@MethodInfo(Name = "将时间转换成指定格式", paramInfo = { "时间" }, returnInfo = "转换结果")
	public static String timeFormat(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT_SECOND);
		if (date != null) {
			return format.format(date);
		}
		return "";
	}

	/**
	 * 将指定的日期字符串转化为日期对象
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param format
	 *            时间格式 如：yyyy-MM-dd HH:mm:ss
	 * @return date
	 * @throws Exception
	 */
	@MethodInfo(Name = "将指定的日期字符串转化为日期对象", paramInfo = { "日期字符串", "日期格式" }, returnInfo = "转换结果")
	public static Date getDate(String dateStr, String format) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			Date date = df.parse(dateStr);
			return date;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 将时间转换成指定格式 yyyy-MM-dd 精确到天
	 * 
	 * @param date
	 *            时间
	 * @return
	 */
	@MethodInfo(Name = "将时间格式精确到天", paramInfo = { "时间" }, returnInfo = "转换结果")
	public static String timeFormatToDay(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT);
		if (date != null) {
			return format.format(date);
		}
		return "";
	}

	/**
	 * 将时间转换成指定格式
	 * 
	 * @param date
	 *            时间
	 * @param model
	 *            时间格式
	 * @return
	 */
	@MethodInfo(Name = "将时间转换成指定格式", paramInfo = { "时间", "时间格式" }, returnInfo = "转换结果")
	public static String timeFormat(Date date, String model) {
		SimpleDateFormat format = new SimpleDateFormat(model);
		if (date != null) {
			return format.format(date);
		}
		return "";
	}

	/**
	 * 获取当前时间的时间戳 精确到毫秒
	 * 
	 * @return
	 */
	@MethodInfo(Name = "获取当前时间戳", returnInfo = "时间戳")
	public static Long getCurrentTimes() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取当天开始的时间戳
	 * 
	 * @return
	 */
	@MethodInfo(Name = "获取当天开始时间戳", returnInfo = "时间戳")
	public static Long getDayBeginTimestamp() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取当天结束的时间戳
	 * 
	 * @return
	 */
	@MethodInfo(Name = "获取当天结束时间戳", returnInfo = "时间戳")
	public static Long getDayEndTimestamp() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 24);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取昨天开始的时间戳
	 * 
	 * @return
	 */
	@MethodInfo(Name = "获取昨天开始时间戳", returnInfo = "时间戳")
	public static Long getYesterdayBeginTimestamp() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取指定格式的当前时间
	 * 
	 * @param format
	 *            格式,如:yyyy-MM-dd
	 * @return
	 */
	@MethodInfo(Name = "获取指定格式当前时间", paramInfo = { "时间格式" }, returnInfo = "时间字符串")
	public static String getSimpleFormat(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

	/**
	 * 获取当前年份
	 * 
	 * @return
	 */
	@MethodInfo(Name = "获取当前年份", returnInfo = "年份")
	public static String getCurrentYear() {
		return new SimpleDateFormat("yyyy").format(new Date());
	}

	/**
	 * 获取特定时间的时间戳
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	@MethodInfo(Name = "获取特定时间时间戳", paramInfo = { "年份", "月份", "日期", "小时", "分钟", "秒" }, returnInfo = "时间戳")
	public static Long getTimestramp(int year, int month, int day, int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, hour, minute, second);
		return calendar.getTimeInMillis();
	}

	/**
	 * 将指定格式 转为毫秒
	 * 
	 * @param date
	 *            时间
	 * @param format
	 *            格式
	 * @return
	 */
	@MethodInfo(Name = "将指定格式转换成毫秒", paramInfo = { "时间字符串", "时间格式" }, returnInfo = "时间戳")
	public static Long getDatemill(String date, String format) {
		Long time = null;
		if (null != date && !date.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				long millionSeconds = sdf.parse(date.toString()).getTime();
				time = millionSeconds;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return time;
	}

	/**
	 * 获取距离某个日期的天数
	 * 
	 * @param time
	 *            格式：yyyy-MM-dd
	 * @return
	 */
	@MethodInfo(Name = "获取距离某个日期的天数", paramInfo = { "时间字符串" }, returnInfo = "天数")
	public static Integer getDisparityDay(String time) {
		Integer days = null;
		if (null != time) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
				long millionSeconds = sdf.parse(time).getTime();
				days = Math.abs((int) ((System.currentTimeMillis() - millionSeconds) / 1000 / 60 / 60 / 24));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return days;
	}

	/**
	 * 获取某天的星期
	 * 
	 * @param date
	 *            格式：yyyy-MM-dd
	 * @return
	 */
	@MethodInfo(Name = "获取某天的星期", paramInfo = { "时间字符串" }, returnInfo = "星期")
	public static String getWEEKDAYS(String date) {
		DateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
		try {
			Date time = df.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(time);
			int weekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
			return WEEKDAYS[weekDay];
		} catch (ParseException e) {
			logger.error("【数据转换出现异常】");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取某年某月最后一天
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份
	 * @return
	 */
	@MethodInfo(Name = "获取某年某月最后一题", paramInfo = { "年份", "月份" }, returnInfo = "天数")
	public static Integer getFinalDay(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date time = cal.getTime();
		Integer finalDay = Integer.valueOf(new SimpleDateFormat("dd").format(time));
		return finalDay;
	}

	/**
	 * 获取某年某月第一天
	 * 
	 * @param date
	 *            时间
	 * @return
	 */
	@MethodInfo(Name = "获取某年某月第一天", paramInfo = { "时间" }, returnInfo = "时间")
	public static Date getStartDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 获取某年某月最后一天
	 * 
	 * @param date
	 *            时间
	 * @return
	 */
	@MethodInfo(Name = "获取某年某月第一天", paramInfo = { "时间" }, returnInfo = "时间")
	public static Date getFinalDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 判断某一年是否闰年
	 * 
	 * @param year
	 *            年份
	 * @return 闰年返回true
	 */
	@MethodInfo(Name = "判断某一年是否闰年", paramInfo = { "年份" }, returnInfo = "判断结果")
	public static Boolean judgeLeapYear(int year) {
		if (getFinalDay(year, Constant.Date.MONTH_FEBRUARY) == Constant.Date.LEAP_YEAR_DAY) {
			return true;
		}
		return false;
	}
}
