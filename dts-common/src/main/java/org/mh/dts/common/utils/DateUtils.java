package org.mh.dts.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by maohong on 2016/12/16.
 */
public class DateUtils {

    public static final String format_Date_Strigula = "yyyy-MM-dd";
    public static final String format_DateTime_Strigula = "yyyy-MM-dd HH:mm:ss";

    private final static String[] chineseWeekNames = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

    /**
     * 根据给定的日期串 转化成日期
     */
    public static Date parseDate(String date, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date ret = formatter.parse(date);
            return ret;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     *根据给定的年、月、日 转化成日期
     */
    public static Date parseDate(int y, int m, int d) {
        //Calendar的月份从0~11,0是1月份，1是二月份，依次类推
        Calendar ca = Calendar.getInstance();
        ca.set(y, m - 1, d, 0, 0);
        return ca.getTime();
    }

    /**
     * 将指定日期格式化为指定格式字符串，格式可以自己定义，也可以采用  format  和 format1
     *
     * 如果格式有问题，返回空字符串
     */
    public static String formatDate(Date date, String format) {
        if (date == null)
            return "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format, java.util.Locale.US);
            String ret = formatter.format(date);
            return ret;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回格式化的日期串
     */
    public static String formatDate(Date date) {
        return formatDate(date, format_Date_Strigula);
    }

    /**
     * 返回格式化的日期时间串
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, format_DateTime_Strigula);
    }


    /**
     * 返回某年某月有多少天
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
