package common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 * 
 * @author TangerineSpecter
 */
public class TimeUtils {
	/** 默认格式 */
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
	/** 时间格式——精确到秒 */
	private static final String DEFAULT_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
	/** 星期数 */
	private static final String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 将时间转换成指定格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetime
	 * 
	 * @return
	 */
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
	 * @param datetime
	 * @return
	 */
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
	 * @param datetime
	 * @param model
	 * @return
	 */
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
	public static Long getCurrentTimes() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取当天开始的时间戳
	 * 
	 * @return
	 */
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
	public static Long getYesterdayBeginTimestamp() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取当前时间戳 格式:YYYYMMDD
	 * 
	 * @return
	 */
	public static String getSimpleFormat() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	/**
	 * 获取当前年份
	 * 
	 * @return
	 */
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
	public static Long getTimestramp(int year, int month, int day, int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, hour, minute, second);
		return calendar.getTimeInMillis();
	}

	/**
	 * 将指定格式 转为毫秒
	 * 
	 * @param date
	 * @return
	 */
	public static Long getDatemill(String date, String format) {
		Long time = null;
		if (null != date && !date.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				long millionSeconds = sdf.parse(date.toString()).getTime();// 毫秒
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
	public static String getWeekDays(String date) {
		DateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
		try {
			Date time = df.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(time);
			int weekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
			return weekDays[weekDay];
		} catch (ParseException e) {
			System.out.println("转换异常...");
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
	public static Integer getFinalDay(Integer year, Integer month) {
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
	public static String getStartDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * 获取某年某月最后一天
	 * 
	 * @param date
	 *            时间
	 * @return
	 */
	public static String getFinalDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * 判断某一年是否闰年
	 * 
	 * @param year
	 *            年份
	 * @return 闰年返回true
	 */
	public static Boolean judgeLeapYear(Integer year) {
		if (getFinalDay(year, Constant.Date.MONTH_FEBRUARY).equals(Constant.Date.LEAP_YEAR_DAY)) {
			return true;
		}
		return false;
	}
}
