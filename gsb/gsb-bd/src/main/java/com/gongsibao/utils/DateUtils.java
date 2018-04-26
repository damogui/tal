package com.gongsibao.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class DateUtils {
	private static final Log logger = LogFactory.getLog(DateUtils.class);

	public static final String YYYYMMDD = "yyyy-MM-dd";

	public static final String YYYYMMDD_ZH = "yyyy年MM月dd日";

	public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY; // 中国周一是一周的第一天

	/**
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date parseDate(String strDate) {
		return parseDate(strDate, null);
	}

	/**
	 * parseDate
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String strDate, String pattern) {
		Date date = null;
		try {
			if (pattern == null) {
				pattern = YYYYMMDD;
			}
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			date = format.parse(strDate);
		} catch (Exception e) {
			logger.error("parseDate error:" + e);
		}
		return date;
	}

	/**
	 * format date
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, null);
	}

	/**
	 * format date
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		String strDate = null;
		try {
			if (pattern == null) {
				pattern = YYYYMMDD;
			}
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			strDate = format.format(date);
		} catch (Exception e) {
			logger.error("formatDate error:", e);
		}
		return strDate;
	}

	/**
	 * 取得日期：年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 取得日期：年
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		return month + 1;
	}

	/**
	 * 取得日期：年
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int da = c.get(Calendar.DAY_OF_MONTH);
		return da;
	}

	/**
	 * 取得当天日期是周几
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week_of_year = c.get(Calendar.DAY_OF_WEEK);
		return week_of_year - 1;
	}

	/**
	 * 取得一年的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week_of_year = c.get(Calendar.WEEK_OF_YEAR);
		return week_of_year;
	}

	/**
	 * getWeekBeginAndEndDate
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getWeekBeginAndEndDate(Date date, String pattern) {
		Date monday = getMondayOfWeek(date);
		Date sunday = getSundayOfWeek(date);
		return formatDate(monday, pattern) + " - " + formatDate(sunday, pattern);
	}
	
	/**获取格式为：2018-02-02 08:08:08
	 * @param date
	 * @return
	 */
	public static String getDateStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		return sdf.format(date);
	}

	/**
	 * 根据日期取得对应周周一日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMondayOfWeek(Date date) {
		Calendar monday = Calendar.getInstance();
		monday.setTime(date);
		monday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
		monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return monday.getTime();
	}

	/**
	 * 根据日期取得对应周周日日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSundayOfWeek(Date date) {
		Calendar sunday = Calendar.getInstance();
		sunday.setTime(date);
		sunday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
		sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return sunday.getTime();
	}

	/**
	 * 转换日期格式字符串
	 *
	 * @param obj
	 * @param pattern
	 * @return
	 */
	public static String dateStr(Object obj, String pattern) {
		if (null == obj) {
			return "";
		}
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(obj);
	}

	/**
	 * 字符串转换成java.util.Date (yyyy-MM-dd HH:mm:ss)
	 *
	 * @param str (yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static Date strToDateTime(String str) {
		if (RegexUtils.isNotDate(str)) {
			throw new RuntimeException("str is not datetime");
		}
		Date date = null;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 取得月的剩余天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getRemainDayOfMonth(Date date) {
		int dayOfMonth = getDayOfMonth(date);
		int day = getPassDayOfMonth(date);
		return dayOfMonth - day;
	}

	/**
	 * 取得月已经过的天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getPassDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得月天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得月第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 取得月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 取得季度第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfSeason(Date date) {
		return getFirstDateOfMonth(getSeasonDate(date)[0]);
	}

	/**
	 * 取得季度最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfSeason(Date date) {
		return getLastDateOfMonth(getSeasonDate(date)[2]);
	}

	/**
	 * 取得季度天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfSeason(Date date) {
		int day = 0;
		Date[] seasonDates = getSeasonDate(date);
		for (Date date2 : seasonDates) {
			day += getDayOfMonth(date2);
		}
		return day;
	}

	
	
	/**
	 * 取得季度剩余天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getRemainDayOfSeason(Date date) {
		return getDayOfSeason(date)- getPassDayOfSeason(date);
	}

	/**
	 * 取得季度已过天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getPassDayOfSeason(Date date) {
		int day = 0;

		Date[] seasonDates = getSeasonDate(date);

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);

		if (month == Calendar.JANUARY || month == Calendar.APRIL || month == Calendar.JULY || month == Calendar.OCTOBER) {// 季度第一个月
			day = getPassDayOfMonth(seasonDates[0]);
		} else if (month == Calendar.FEBRUARY || month == Calendar.MAY || month == Calendar.AUGUST || month == Calendar.NOVEMBER) {// 季度第二个月
			day = getDayOfMonth(seasonDates[0]) + getPassDayOfMonth(seasonDates[1]);
		} else if (month == Calendar.MARCH || month == Calendar.JUNE || month == Calendar.SEPTEMBER || month == Calendar.DECEMBER) {// 季度第三个月
			day = getDayOfMonth(seasonDates[0]) + getDayOfMonth(seasonDates[1]) + getPassDayOfMonth(seasonDates[2]);
		}
		return day;
	}

	/**
	 * 取得季度月
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] getSeasonDate(Date date) {
		Date[] season = new Date[3];

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		int nSeason = getSeason(date);
		if (nSeason == 1) {// 第一季度
			c.set(Calendar.MONTH, Calendar.JANUARY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.FEBRUARY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MARCH);
			season[2] = c.getTime();
		} else if (nSeason == 2) {// 第二季度
			c.set(Calendar.MONTH, Calendar.APRIL);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MAY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.JUNE);
			season[2] = c.getTime();
		} else if (nSeason == 3) {// 第三季度
			c.set(Calendar.MONTH, Calendar.JULY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.AUGUST);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.SEPTEMBER);
			season[2] = c.getTime();
		} else if (nSeason == 4) {// 第四季度
			c.set(Calendar.MONTH, Calendar.OCTOBER);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.NOVEMBER);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			season[2] = c.getTime();
		}
		return season;
	}

	/**
	 * 
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {

		int season = 0;

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}
	/**
     * 计算2个日期相差天数
     * @param date1 第一个日期   
     * @param date2 第二个日期
     * @return	data2 - date1 相差的天数    
     */
    public static int differentDays(Date date1,Date date2){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2){
        	//同一年
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++){
                if(i%4==0 && i%100!=0 || i%400==0){
                	//闰年   
                    timeDistance += 366;
                }
                else{
                	//不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1) ;
        }
        else{
        	//不同年
            return day2-day1;
        }
    }
    /** 
     * 判断时间是否在时间段内
     * @param date	当前时间 yyyy-MM-dd HH:mm:ss 
     * @param strDateBegin	开始时间 00:00:00 
     * @param strDateEnd	结束时间 00:05:00 
     * @return 
     */  
    public static boolean isInDate(Date date, String strDateBegin,String strDateEnd) {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String strDate = sdf.format(date);  
        // 截取当前时间时分秒  
        int strDateH = Integer.parseInt(strDate.substring(11, 13));  
        int strDateM = Integer.parseInt(strDate.substring(14, 16));  
        int strDateS = Integer.parseInt(strDate.substring(17, 19));  
        // 截取开始时间时分秒  
        int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));  
        int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));  
        int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));  
        // 截取结束时间时分秒  
        int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));  
        int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));  
        int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));  
        if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {  
            // 当前时间小时数在开始时间和结束时间小时数之间  
            if (strDateH > strDateBeginH && strDateH < strDateEndH) {  
                return true;  
                // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间  
            } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM  
                    && strDateM <= strDateEndM) {  
                return true;  
                // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间  
            } else if (strDateH == strDateBeginH && strDateM == strDateBeginM  
                    && strDateS >= strDateBeginS && strDateS <= strDateEndS) {  
                return true;  
            }  
            // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数  
            else if (strDateH >= strDateBeginH && strDateH == strDateEndH  
                    && strDateM <= strDateEndM) {  
                return true;  
                // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数  
            } else if (strDateH >= strDateBeginH && strDateH == strDateEndH  
                    && strDateM == strDateEndM && strDateS <= strDateEndS) {  
                return true;  
            } else {  
                return false;  
            }  
        } else {  
            return false;  
        }  
    } 
}
