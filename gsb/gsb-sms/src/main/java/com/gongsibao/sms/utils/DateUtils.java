package com.gongsibao.sms.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.gongsibao.sms.utils.security.NumberUtils;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils {


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static Calendar startDate = Calendar.getInstance();
    private static Calendar endDate = Calendar.getInstance();
    private static DateFormat df = DateFormat.getDateInstance();
    private static Date earlydate = new Date();
    private static Date latedate = new Date();

    /**
     * 转换日期格式字符串 (yyyy-MM-dd)
     *
     * @param obj
     * @return
     */
    public static String dateStr(Object obj) {
        if (null == obj) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(obj);
    }

    /**
     * 转换日期格式字符串 (yyyy-MM-dd HH:mm:ss)
     *
     * @param obj
     * @return
     */
    public static String dateTimeStr(Object obj) {
        if (null == obj) {
            return "";
        }

        if ("0000-00-00 00:00:00".equals(obj.toString())) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(obj);
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
     * 将字符串转换成java.util.Date (yyyy-MM-dd)
     *
     * @param str (yyyy-MM-dd)
     * @return
     */
    public static Date strToDate(String str) {
        if (RegexUtils.isNotDate(str)) {
            //throw new RuntimeException("str is not date");
            return null;
        }

        Date date = null;
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
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
     * yyyy-MM-dd HH:mm:ss.fff
     *
     * @param str
     * @return
     */
    public static Date strMsToDateTime(String str) {
        return strToDateTime(StringUtils.trimToEmpty(str).split("\\.")[0]);
    }

    public static Date getMinTime(Date dt) {
        Date dt1 = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
            dt1 = sf.parse(sf.format(dt));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("date formate error ：" + dt + ".   " + e.getMessage());
        }
        return dt1;
    }

    /**
     * 计算两个日期相差的小时数
     *
     * @param beforeDate
     * @param afterDate
     * @return
     */
    public static long getDistinceHour(Date beforeDate, Date afterDate) {
        long dayCount = 0;
        dayCount = (afterDate.getTime() - beforeDate.getTime()) / (60 * 60 * 1000);
        return dayCount;
    }

    /**
     * 计算两个日期相差的天数
     *
     * @param beforeDate
     * @param afterDate
     * @return
     */
    public static long getDistinceDay(Date beforeDate, Date afterDate) {
        long dayCount = 0;
        dayCount = (afterDate.getTime() - beforeDate.getTime()) / (24 * 60 * 60 * 1000);
        return dayCount;
    }

    /**
     * 计算两个日期相差的年数
     *
     * @param beforeDate
     * @param afterDate
     * @return
     */
    public static int getDistinceYear(Date beforeDate, Date afterDate) {
        try {
            String beforeYear = DateFormatUtils.format(beforeDate, "yyyy");
            String afterYear = DateFormatUtils.format(afterDate, "yyyy");
            if (StringUtils.isNotBlank(beforeYear) && StringUtils.isNotBlank(afterYear)) {
                return Math.abs(Integer.parseInt(afterYear) - Integer.parseInt(beforeYear));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 当天到第二天12点的秒数
     *
     * @return
     */
    public static int exipireTime() {
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate.get(Calendar.YEAR),
                curDate.get(Calendar.MONTH), curDate.get(Calendar.DATE) + 1, 0, 0, 0);
        long timeCap = tommorowDate.getTimeInMillis() - System.currentTimeMillis();
        return (int) timeCap / 1000;
    }

    /**
     * 返回当前时间前指定年数列表
     *
     * @param count
     * @return
     * @author
     */
    public static List<Integer> getBeforeYear(int count) {
        Calendar curDate = Calendar.getInstance();
        int nowYear = curDate.get(Calendar.YEAR);
        List<Integer> yearList = new ArrayList<Integer>();
        for (int i = nowYear; i > (nowYear - count); i--) {
            yearList.add(i);
        }
        return yearList;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static int checkTimeDis(Date begin, Date end, int distance) {
        if (begin == null) {
            // 时间未选择
            return -1;
        }

        if (end == null) {
            end = new Date();
        }

        if (getDistinceDay(begin, end) > distance) {
            return -2;
        }

        return 1;
    }

    public static Date getDayByCount(Date curDatetime, int count) {
        // 设置日期格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        long longTime = 24 * 60 * 60 * 1000 * count;
        try {
            Date changeDate = formatter.parse(dateStr(curDatetime));
            longTime += changeDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 根据毫秒获得日期
        Date newDate = new Date(longTime);
        // 日期格式转换为字符串
        return newDate;
    }

    /**
     * 取系统当前时间:返回值为如下形式
     * 2002-10-30
     *
     * @return String
     */
    public static String getYYYY_MM_DD(Date date) {
        return dateToString(date).substring(0, 10);

    }

    public static String dateToString(Date time) {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ctime = formatter.format(time);

        return ctime;
    }

    public static Date getDateByDateStr2(String dateStr) {
        // 设置日期格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        // 根据毫秒获得日期
        Date newDate = new Date();
        try {
            newDate = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 日期格式转换为字符串
        return newDate;
    }

    public static Date getDateByDateStr(String dateStr) {
        // 设置日期格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        // 根据毫秒获得日期
        Date newDate = new Date();
        if (dateStr.contains(",")) {
            String[] datesplit = dateStr.split(",");
            dateStr = datesplit[datesplit.length - 1];
            if (StringUtils.isBlank(dateStr)) {
                dateStr = datesplit[0];
            }
        }
        if (StringUtils.isBlank(StringUtils.trimToEmpty(dateStr))) return null;
        try {
            newDate = formatter.parse(dateStr);
        } catch (ParseException e) {
            try {
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy年MM月dd日");
                newDate = formatter1.parse(dateStr);
            } catch (Exception e1) {

                try {
                    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
                    newDate = formatter2.parse(dateStr);
                } catch (Exception e2) {
                    SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    try {
                        newDate = formatter3.parse(dateStr);
                    } catch (ParseException e3) {
                        SimpleDateFormat formatter4 = new SimpleDateFormat("yyyy/MM/dd");
                        try {
                            newDate = formatter4.parse(dateStr);
                        } catch (ParseException e4) {

                        }

                    }
                }
            }
        }
        // 日期格式转换为字符串
        return newDate;
    }

    // 获得昨天日期
    public static Date getYesterday(Date date) {
        date = null == date ? new Date() : date;
        return addDays(date, -1);
    }

    // 获得本周一的日期
    public static Date getWeekBegin(Date date) {
        date = null == date ? new Date() : date;

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 客服部刘立丹提的 每周第一天是上周四
    public static Date getWeekBeginForLiuLiDan(Date date) {
        int delay = -3;
        if (getWeekNum(date) >= 5) {
            delay = 4;
        }
        return addDays(getWeekBegin(date), delay);
    }

    // 获得本周日的日期
    public static Date getWeekEnd(Date date) {
        date = null == date ? new Date() : date;
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(getWeekBegin(date));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.getTime();
    }

    // 客服部刘立丹提的 每周第一天是本周五
    public static Date getWeekEndForLiuLiDan(Date date) {
        int delay = -3;
        if (getWeekNum(date) >= 5) {
            delay = 4;
        }
        return addDays(getWeekEnd(date), delay);
    }

    // 获得本月第一天日期
    public static Date getMonthBegin(Date date) {
        date = null == date ? new Date() : date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    // 获得本月最后一天日期
    public static Date getMonthEnd(Date date) {
        date = null == date ? new Date() : date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        return cal.getTime();
    }

    //获得上月第一天日期
    public static Date getLastMonthBegin(Date date) {
        date = null == date ? new Date() : date;
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, -1);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();
    }

    //获得上月最后一天日期
    public static Date getLastMonthEnd(Date date) {
        date = null == date ? new Date() : date;
        Calendar cale = Calendar.getInstance();
        int month = cale.get(Calendar.MONTH);
        cale.set(Calendar.MONTH, month - 1);
        cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
        ;
        return cale.getTime();
    }


    //region 相差算法

    /**
     * 计算两个时间相差多少个年
     *
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    public static int yearsBetween(String start, String end) throws ParseException {
        startDate.setTime(sdf.parse(start));
        endDate.setTime(sdf.parse(end));
        return (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR));
    }

    /**
     * 计算两个时间相差多少个月
     *
     * @param start <String>
     * @param end   <String>
     * @return int
     * @throws ParseException
     */
    public static int monthsBetween(String start, String end) {

        int result = 0;
        try {
            startDate.setTime(sdf.parse(start));
            endDate.setTime(sdf.parse(end));
            result = yearsBetween(start, end) * 12 + endDate.get(Calendar.MONTH) - startDate.get(Calendar.MONTH);
            result = Math.abs(result);
        } catch (ParseException e) {
            result = -1;
        }
        return result;

    }

    /**
     * 计算两个时间相差多少个天
     *
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String start, String end) throws ParseException {
        // 得到两个日期相差多少天
        return hoursBetween(start, end) / 24;
    }

    /**
     * 计算两个时间相差多少小时
     *
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    public static int hoursBetween(String start, String end) throws ParseException {
        // 得到两个日期相差多少小时
        return minutesBetween(start, end) / 60;
    }

    /**
     * 计算两个时间相差多少分
     *
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    public static int minutesBetween(String start, String end) throws ParseException {
        // 得到两个日期相差多少分
        return secondesBetween(start, end) / 60;
    }

    /**
     * 计算两个时间相差多少秒
     *
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    public static int secondesBetween(String start, String end) throws ParseException {
        earlydate = df.parse(start);
        latedate = df.parse(end);
        startDate.setTime(earlydate);
        endDate.setTime(latedate);
        // 设置时间为0时
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.HOUR_OF_DAY, 0);
        endDate.set(Calendar.MINUTE, 0);
        endDate.set(Calendar.SECOND, 0);
        // 得到两个日期相差多少秒
        return ((int) (endDate.getTime().getTime() / 1000) - (int) (startDate.getTime().getTime() / 1000));
    }

    // endregion

    public static int getWeekNum(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int num = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (num == 0) {
            num = 7;
        }
        return num;
    }

    //获取上周五
    public static String lastFirday() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 7 - dayOfWeek;
        calendar.add(Calendar.DATE, offset - 9);

        String Date = new SimpleDateFormat("yyyy-MM-dd ").format(calendar.getTime());
        return Date;//这是从上周日开始数的到本周五为6
    }


    /**
     * 返回1时（dt1>dt2）;0是相等；-1时dt1<dt2
     *
     * @param dt1
     * @param dt2
     * @return
     */
    public static int compareDate(Date dt1, Date dt2) {
        if (dt1 == null && dt2 == null) return 0;
        if (dt1 == null) return -1;
        if (dt2 == null) return 1;
        if (dt1.getTime() > dt2.getTime()) {
            return 1;
        } else if (dt1.getTime() < dt2.getTime()) {
            return -1;
        } else {//相等
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(dateStr(getWeekBegin(new Date())));
        System.out.println(dateStr(getWeekEnd(new Date())));
        System.out.println(dateStr(getWeekBeginForLiuLiDan(new Date())));
        System.out.println(dateStr(getWeekEndForLiuLiDan(new Date())));

        System.out.println(dateStr(getWeekBeginForLiuLiDan(strToDate("2016-07-31"))));
        System.out.println(dateStr(getWeekEndForLiuLiDan(strToDate("2016-07-31"))));

        System.out.println(getWeekNum(strToDate("2016-10-21")));

        System.out.println("----" + dateTimeStr(getDateByDateStr("2017-03-02T19:48:08+08:00")));

        System.out.println("----" + getDateByDateStr("2015/3/4 0:00:00"));

        Date endTime = getDayByCount(new Date(), 3);
        System.out.println("+++" + dateTimeStr(endTime));


        System.out.println(getDistinceDay(strToDate(dateStr(new Date())), endTime));
        System.out.println(getDistinceDay(new Date(), endTime));

        System.out.println(getDistinceDay(endTime, new Date()));

        System.out.println(getDistinceHour(strToDateTime("2017-05-03 14:52:19"), strToDateTime("2017-05-03 18:52:19")));

        System.out.println(getDistinceDay(strToDateTime("2017-05-03 14:52:19"), new Date()));
        String d1 = "2016-02-07至2026-02-06";
        String d2 = "2015/08/07 - 2025/08/06";

        Date nowDate = new Date();

        System.out.println(DateUtils.getDateByDateStr(StringUtils.trimToEmpty(d1.split("至")[0])));
        System.out.println(DateUtils.getDateByDateStr(StringUtils.trimToEmpty(d1.split("至")[1])));

        System.out.println(DateUtils.getDateByDateStr(StringUtils.trimToEmpty(d2.split("-")[0])));
        System.out.println(DateUtils.getDateByDateStr(StringUtils.trimToEmpty(d2.split("-")[1])));

        Calendar cal_start = Calendar.getInstance();
        Calendar cal_end = Calendar.getInstance();
        cal_start.setTime(new Date());
        cal_end.setTime(DateUtils.strToDate("2018-09-27 12:12:12"));
        int handleDayCount = DateUtils.getWorkingDay(cal_start, cal_end);

        System.out.println("handleDayCount:" + 73500L * 100);

        // 2006-05-22
        // 2015-10-30  2016-06-14  2015-04-20  2010-08-12"  2015-05-13   2015-06-08

        List<Integer> years = new ArrayList<Integer>();

        years.add(NumberUtils.toInt(DateUtils.dateStr(DateUtils.strToDate("2015-10-30"), "yyyyMMdd")));
        years.add(NumberUtils.toInt(DateUtils.dateStr(DateUtils.strToDate("2016-06-14"), "yyyyMMdd")));
        years.add(NumberUtils.toInt(DateUtils.dateStr(DateUtils.strToDate("2015-04-20"), "yyyyMMdd")));
        years.add(NumberUtils.toInt(DateUtils.dateStr(DateUtils.strToDate("2010-08-12"), "yyyyMMdd")));
        years.add(NumberUtils.toInt(DateUtils.dateStr(DateUtils.strToDate("2015-05-13"), "yyyyMMdd")));
        years.add(NumberUtils.toInt(DateUtils.dateStr(DateUtils.strToDate("2015-06-08"), "yyyyMMdd")));

        Collections.sort(years);

        long n = 0;
        for (int i = 0; i < years.size(); i++) {
            int y = years.get(i);
            System.out.println("y:" + y);
            if (i == 0) {
                n += DateUtils.getDistinceDay(DateUtils.strToDate("2006-05-22"),
                        DateUtils.strToDate(StringUtils.trimToEmpty(y).substring(0, 4) + "-" + StringUtils.trimToEmpty(y).substring(4, 6) + "-" + StringUtils.trimToEmpty(y).substring(6, 8)));
            } else {
                int y1 = years.get(i - 1);
                n += DateUtils.getDistinceDay(DateUtils.strToDate(StringUtils.trimToEmpty(y1).substring(0, 4) + "-" + StringUtils.trimToEmpty(y1).substring(4, 6) + "-" + StringUtils.trimToEmpty(y1).substring(6, 8)),
                        DateUtils.strToDate(StringUtils.trimToEmpty(y).substring(0, 4) + "-" + StringUtils.trimToEmpty(y).substring(4, 6) + "-" + StringUtils.trimToEmpty(y).substring(6, 8)));
            }
        }
        System.out.println(n);
        System.out.println(NumberUtils.toInt(n) / years.size());

        //20160614  612

        System.out.println(DateUtils.dateStr(DateUtils.addDays(DateUtils.strToDate(StringUtils.trimToEmpty("20160614").substring(0, 4) + "-" + StringUtils.trimToEmpty("20160614").substring(4, 6) + "-" + StringUtils.trimToEmpty("20160614").substring(6, 8))
                , NumberUtils.toInt(n) / years.size())));


        System.out.println(DateUtils.dateStr(DateUtils.addDays(DateUtils.strToDate(StringUtils.trimToEmpty(years.get(years.size() - 1)).substring(0, 4) + "-" + StringUtils.trimToEmpty(years.get(years.size() - 1)).substring(4, 6) + "-" + StringUtils.trimToEmpty(years.get(years.size() - 1)).substring(6, 8)), NumberUtils.toInt(n) / years.size())));

        System.out.println(dateStr(addDays(strToDate("2016-06-14"), 612)));

        System.out.println("date:" + getYYYY_MM_DD(new Date()));


        int differenceMouth = monthsBetween(getYYYY_MM_DD(new Date()), "2017-1-08");
        System.out.println("differenceMouth:" + differenceMouth);

    }

    /**
     * 获取季度开始时间
     *
     * @return
     */
    public static String getCurrentQuarterBeginTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        String now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = DateUtils.dateStr(c.getTime(), "yyyy-MM-dd") + " 00:00:00";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取季度结束时间
     *
     * @return
     */
    public static String getCurrentQuarterEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        String now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = DateUtils.dateStr(c.getTime(), "yyyy-MM-dd") + " 23:59:59";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取上周开始时间
     *
     * @return
     */
    public static String getLastWeekBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 1 - dayOfWeek;
        calendar.add(Calendar.DATE, offset - 7);

        String lastBeginDate = new SimpleDateFormat("yyyy-MM-dd ").format(calendar.getTime()) + "00:00:00";

        return lastBeginDate;
    }

    /**
     * 获取上周结束时间
     *
     * @return
     */
    public static String getLastWeekEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 7 - dayOfWeek;
        calendar.add(Calendar.DATE, offset - 7);

        String lastEndDate = new SimpleDateFormat("yyyy-MM-dd ").format(calendar.getTime()) + " 23:59:59";
        return lastEndDate;
    }

    public static int getDaysBetween(java.util.Calendar d1, java.util.Calendar d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(java.util.Calendar.DAY_OF_YEAR)
                - d1.get(java.util.Calendar.DAY_OF_YEAR);
        int y2 = d2.get(java.util.Calendar.YEAR);
        if (d1.get(java.util.Calendar.YEAR) != y2) {
            d1 = (java.util.Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
                d1.add(java.util.Calendar.YEAR, 1);
            } while (d1.get(java.util.Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 计算2个日期之间的相隔天数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getWorkingDay(java.util.Calendar d1, java.util.Calendar d2) {
        int result = -1;
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }

        int betweendays = getDaysBetween(d1, d2);


        int charge_date = 0;
        int charge_start_date = 0;//开始日期的日期偏移量
        int charge_end_date = 0;//结束日期的日期偏移量
        // 日期不在同一个日期内
        int stmp;
        int etmp;
        stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
        etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);
        if (stmp != 0 && stmp != 6) {// 开始日期为星期六和星期日时偏移量为0
            charge_start_date = stmp - 1;
        }
        if (etmp != 0 && etmp != 6) {// 结束日期为星期六和星期日时偏移量为0
            charge_end_date = etmp - 1;
        }
//  }
        result = (getDaysBetween(getNextMonday(d1), getNextMonday(d2)) / 7)
                * 5 + charge_start_date - charge_end_date;
        //System.out.println("charge_start_date>" + charge_start_date);
        //System.out.println("charge_end_date>" + charge_end_date);
        //System.out.println("between day is-->" + betweendays);
        return result;
    }

    /**
     * 获得日期的下一个星期一的日期
     *
     * @param date
     * @return
     */
    public static Calendar getNextMonday(Calendar date) {
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        } while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }

    public static String getChineseWeek(Calendar date) {
        final String dayNames[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
                "星期六"};

        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);

        // System.out.println(dayNames[dayOfWeek - 1]);
        return dayNames[dayOfWeek - 1];

    }


}
