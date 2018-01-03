package common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 * 
 * @author TangerineSpecter
 */
public class TimeUtils {

	/**
	 * 将时间转换成指定格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetime
	 * 
	 * @return
	 */
	public static String timeFormat(Date datetime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (datetime != null) {
			return format.format(datetime);
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
	public static String timeFormatToDay(Date datetime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (datetime != null) {
			return format.format(datetime);
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
	public static String timeFormat(Date datetime, String model) {
		SimpleDateFormat format = new SimpleDateFormat(model);
		if (datetime != null) {
			return format.format(datetime);
		}
		return "";
	}

	/**
	 * 获取当前时间的时间戳 精确到毫秒
	 * 
	 * @return
	 */
	public static Long getCurrentTimestamp() {
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
	public static String getPayCurrTs() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
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
	 * 将yyyy-MM-dd格式 转为毫秒
	 * 
	 * @param date
	 * @return
	 */
	public static Long getDatemill(String date) {
		Long time = null;
		if (null != date && !date.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				long millionSeconds = sdf.parse(date.toString()).getTime();// 毫秒
				time = millionSeconds;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return time;
	}
}
