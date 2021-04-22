package com.qintingshuang.web.utils;

import com.qintingshuang.web.enums.LimitEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import static com.qintingshuang.web.enums.LimitEnum.FIRST_LIMIT;
import static com.qintingshuang.web.enums.LimitEnum.LAST_LIMIT;

/**
 * @author qintingshuang
 * @create 2021-04-22 16:14
 * @description 时间工具类
 **/
public class DateUtils {


    /**
     * 从今天开始计算时间
     *
     * @param num 计算天数
     * @return 计算结果
     */
    public static Date getLimitDay(int num) {
        num = 0 - num;
        Calendar ca = Calendar.getInstance();
        // num为增加的天数，可以改变的
        ca.add(Calendar.DATE, num);
        Date limitDay = ca.getTime();
        return limitDay;
    }


    /**
     * 获取指定日期的差num的数据
     *
     * @param day    指定日期
     * @param num    相差天数
     * @param format 时间格式
     * @return
     * @throws ParseException
     */
    public static String getDiffDay(String day, int num, String format) throws ParseException {
        Calendar c = Calendar.getInstance();
        Date date = new SimpleDateFormat(format).parse(day);
        c.setTime(date);
        c.add(Calendar.DATE, num);
        return new SimpleDateFormat(format).format(c.getTime());
    }

    /**
     * 获得date类型的日期
     *
     * @param date   日期
     * @param format 格式
     * @return
     * @throws ParseException
     */
    public static Date getDateTime(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }


    /**
     * 获得String类型的日期 可以是月份、年份、日期、时间
     *
     * @param date   日期
     * @param format 格式
     * @return
     * @throws ParseException
     */
    public static String getDayStr(Date date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }


    /**
     * 时间比较
     * <p>
     * 当 result>0，firstTime>secondTime
     * 当 result<0，firstTime<secondTime
     *
     * @param firstTime
     * @param secondTime
     * @param formatTime yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static Integer getCompareResult(String firstTime, String secondTime, String formatTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatTime);
        Date date1 = format.parse(firstTime);
        Date date2 = format.parse(secondTime);
        int result = date1.compareTo(date2);
        return result;
    }

    /**
     * 判断两个时间大小
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int compareTwoTime(String time1, String time2) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        int flagValue = 0;
        try {
            Date date1, date2;
            date1 = simpleDateFormat.parse(time1);
            date2 = simpleDateFormat.parse(time2);
            long millisecond = date1.getTime() - date2.getTime();
            if (millisecond > 0) {
                flagValue = 1;
            } else if (millisecond < 0) {
                flagValue = -1;
            } else if (millisecond == 0) {
                flagValue = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (flagValue);
    }

    /**
     * 判断两个时间大小
     */
    public static int compareTwoTime(Date time1, Date time2) {
        int flagValue = 0;
        try {
            long millisecond = time1.getTime() - time2.getTime();
            if (millisecond > 0) {
                flagValue = 1;
            } else if (millisecond < 0) {
                flagValue = -1;
            } else if (millisecond == 0) {
                flagValue = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (flagValue);
    }

    /**
     * 比较两个时间相差天数
     */
    public static float calculateTimeGapDay(String time1, String time2) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        float day = 0;
        Date date1 = null;
        Date date2 = null;

        try {
            date1 = simpleDateFormat.parse(time1);
            date2 = simpleDateFormat.parse(time2);
            long millisecond = date2.getTime() - date1.getTime();
            day = millisecond / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (day);
    }

    /**
     * 比较两个时间相差天数
     */
    public static float calculateTimeGapDay(Date time1, Date time2) {
        float day = 0;
        try {
            Date date1, date2;
            date1 = time1;
            date2 = time2;
            long millisecond = date2.getTime() - date1.getTime();
            day = millisecond / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (day);
    }

    /**
     * 比较两个时间相差小时
     */
    public static double calculatetimeGapHour(String time1, String time2) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        double hour = 0;
        try {
            Date date1, date2;
            date1 = simpleDateFormat.parse(time1);
            date2 = simpleDateFormat.parse(time2);
            double millisecond = date2.getTime() - date1.getTime();
            hour = millisecond / (60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hour;
    }

    /**
     * 比较两个时间相差小时
     */
    public static double calculatetimeGapHour(Date date1, Date date2) {
        double hour = 0;
        double millisecond = date2.getTime() - date1.getTime();
        hour = millisecond / (60 * 60 * 1000);
        return hour;
    }

    /**
     * 比较两个时间相差分钟
     */
    public static double calculatetimeGapMinute(String time1, String time2) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        double minute = 0;
        try {
            Date date1, date2;
            date1 = simpleDateFormat.parse(time1);
            date2 = simpleDateFormat.parse(time2);
            double millisecond = date2.getTime() - date1.getTime();
            minute = millisecond / (60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return minute;
    }

    /**
     * 比较两个时间相差分钟
     */
    public static double calculatetimeGapMinute(Date date1, Date date2) {
        double minute = 0;
        double millisecond = date2.getTime() - date1.getTime();
        minute = millisecond / (60 * 1000);
        return minute;
    }

    /**
     * 比较两个时间相差秒
     */
    public static double calculatetimeGapSecond(String time1, String time2) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        double second = 0;
        try {
            Date date1, date2;
            date1 = simpleDateFormat.parse(time1);
            date2 = simpleDateFormat.parse(time2);
            double millisecond = date2.getTime() - date1.getTime();
            second = millisecond / (1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return second;
    }

    /**
     * 比较两个时间相差秒
     */
    public static double calculatetimeGapSecond(Date date1, Date date2) {
        double second = 0;
        double millisecond = date2.getTime() - date1.getTime();
        second = millisecond / (1000);
        return second;
    }


    /**
     * 获取dateStr相差i的dateType的日期（格式为format）
     *
     * @param dateStr  指定日期
     * @param i        相差数值
     * @param dateType 日期类型 Calendar.YEAR  Calendar.MONTH  Calendar.DAY
     * @param format   格式
     * @return
     * @throws ParseException
     */
    public static String getAcrossTime(String dateStr, int i, int dateType, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateType, -i);
        return simpleDateFormat.format(calendar.getTime());
    }



    /**
     * yyyy-MM-dd HH:mm:ss 格式校验
     *
     * @param time
     * @return
     */
    public static boolean boolDayTimeFormat(String time) {
        String pattern = "^((([12][0-9][0-9][0-9]-(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|(20[0-3][0-9]-(0[2469]|11)-(0[1-9]|[12][0-9]|30))) (20|21|22|23|[0-1][0-9]):[0-5][0-9]:[0-5][0-9])$";
        return Pattern.matches(pattern, time);
    }


    /**
     * 获取时间的最后一秒和第一秒
     * 默认是开始日期
     * @param day    指定日期
     * @param limit  最后一次还是第一次
     * @param format 格式
     * @return
     */
    public static String getLimitTimeOfDay(Date day, LimitEnum limit, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String limitTimeOfDay = simpleDateFormat.format(day) + " 00:00:00";
        if (FIRST_LIMIT.equals(limit)) {
            limitTimeOfDay = simpleDateFormat.format(day) + " 00:00:00";
        } else if (LAST_LIMIT.equals(limit)) {
            limitTimeOfDay = simpleDateFormat.format(day) + " 23:59:59";
        }
        return limitTimeOfDay;
    }


    /**
     * 获取当月的第一天或者最后一天
     *
     * @param limit  最后一天或者第一天
     * @param format 格式
     * @return
     */
    public static String getLimitDayOfCurrentMonth(LimitEnum limit, String format) {
        // 获取当前年份、月份、日期
        Calendar cale = Calendar.getInstance();
        // 获取当月第一天和最后一天
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        // 第一天
        if (FIRST_LIMIT.equals(limit)) {
            cale.add(Calendar.MONTH, 0);
            cale.set(Calendar.DAY_OF_MONTH, 1);
        } else if (LAST_LIMIT.equals(limit)) {
            //最后一天
            cale.add(Calendar.MONTH, 1);
            cale.set(Calendar.DAY_OF_MONTH, 0);
        }
        return simpleDateFormat.format(cale.getTime());

    }
}
