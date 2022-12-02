package com.ylkj.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * 日期处理
 *
 * @author ljw
 * 
 * 
 * @date 2022年11月27日-下午4:30:19
 */
public class DateUtils {

	/** 时间格式(yyyy) */
	public final static String DATE_YEAR = "yyyy";
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyMMdd) */
	public final static String DATE_GZ_PATTERN = "yyMMdd";
	/** 时间格式(MMdd) */
	public final static String DATE_GZ_PATTERN_TWO = "yyyy-M-dd";
	/** 时间格式(MMdd) */
	public final static String DATE_GZ_PATTERN_THREE = "yyyy/M/dd";
	/** 时间格式(yyMMddHHmmss) */
	public final static String DATE_PICTURE_PATTERN = "yyMMddHHmmss";

	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/** 时间格式(yyyy年M月dd日 ah:mm:ss) 代码生成器使用 */
	public final static String DATE_TIME_CHN_PATTERN = "yyyy年M月dd日 ah:mm:ss";

	public static String format(Date date) {
		return format(date, DATE_PATTERN);
	}

	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}

	/**
	 * String 转 Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date formactStringToDate(String date) {
		return formactStringToDate(date, DATE_TIME_PATTERN);
	}

	public static Date formactStringToDate(String dateTime, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = dateFormat.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;

	}

	/**
	 * 小时分钟秒 To Date
	 * 
	 * @param strDate
	 */
	public static Date HMSToDate(String strDate) {
		Date dateTime = new Date();
		// DATE_PATTERN = "yyyy-MM-dd"
		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
		// 获取当前时间的年月日
		String dateString = format.format(dateTime);
		// 将传入的小时、分钟、秒与当前年月日拼接成字符串
		String endDateString = dateString + " " + strDate;
		// DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_PATTERN);
		ParsePosition pos = new ParsePosition(0);
		// 将拼接成的字符转时间
		Date strtodate = formatter.parse(endDateString, pos);
		return strtodate;
	}

	/**
	 * 相应的时区时间转为标准时间
	 * 
	 * @return
	 */
	public static Date TimeToUtcDate(HttpServletRequest request, Date utcDate) {
		if (request == null || utcDate == null) {
			return null;
		}
		String time = request.getHeader("time");
		if (time == null) {
			return utcDate;
		}
		Long date = utcDate.getTime();
		return new Date(date - Long.parseLong(time) * 1000);
	}

	/**
	 * 判断当前时间是否在【startTime，endTime】之间
	 * 
	 * @param nowTime
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime,
			Date endTime) {
		if (nowTime.getTime() == startTime.getTime()
				|| nowTime.getTime() == endTime.getTime()) {
			return true;
		}
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}
}
