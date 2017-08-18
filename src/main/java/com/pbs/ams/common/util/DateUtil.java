package com.pbs.ams.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
    public static final String PATTERN_STANDARD = "yyyyMMddHHmmssSSS";

    public static final String PATTERN_DATE = "yyyy-MM-dd";
    /**
     * 按照 yyyyMMddHHmmssSSS的格式，日期转字符串
     * @param date
     * @return yyyyMMddHHmmssSSS
     */
    public static Long dateToLong(Date date){
        return dateToLong(date,PATTERN_DATE);
    }

    /**
     * 按照参数format的格式，日期转Integer
     * @param date
     * @param format
     * @return
     */
    public static Long dateToLong(Date date,String format){
        if(date!=null){
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return Long.valueOf(sdf.format(date));
        }else{
            return 0L;
        }
    }
    /**
     * String类型转换Date
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (strDate == null || strDate.equals("")) {
            throw new RuntimeException("str date null");
        }
        if (pattern == null || pattern.equals("")) {
            pattern = DateUtil.PATTERN_DATE;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;

        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
    /**
     *
     * @comment 获取月份
     * @return
     * 2015年11月2日
     * 下午2:10:24
     */
    public static int getMonth(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }
    /**
     *
     * @comment 获取天
     * @return
     * 2015年11月2日
     * 下午2:10:34
     */
    public static int getDay(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }
    /**
     *
     * @comment 获取年份
     * @return
     * 2015年11月2日
     * 下午2:10:49
     */
    public static int getYear(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
    /**
     *
     * @comment 获取当前小时
     * @return
     * 2015年11月30日
     * 下午4:47:59
     */
    public static int getHour(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    /**
     *
     * @comment 获取当前分钟
     * @return
     * 2015年11月30日
     * 下午4:49:02
     */
    public static int getMinute(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }
    public static long getStartDate(){
        Calendar calEnviron = Calendar.getInstance();
        // 每天的00:00.am开始执行
        calEnviron.set(Calendar.HOUR_OF_DAY, 0);
        calEnviron.set(Calendar.MINUTE, 00);
        // date为制定时间
        Date dateSetter = new Date();
        dateSetter = calEnviron.getTime();
        // nowDate为当前时间
        Date nowDateSetter = new Date();
        // 所得时间差为，距现在待触发时间的间隔
        long intervalEnviron = dateSetter.getTime() - nowDateSetter.getTime();
        if (intervalEnviron < 0) {
            calEnviron.add(Calendar.DAY_OF_MONTH, 1);
            dateSetter = calEnviron.getTime();
            intervalEnviron = dateSetter.getTime() - nowDateSetter.getTime();
        }
        return intervalEnviron;
    }
    /**
     *
     * @comment 时间戳转字符串
     * @return
     * 2015年12月18日
     * 下午6:24:34
     */
    public static String  timestampToDateString(long timestamp){
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_STANDARD);
        return dateFormat.format(new Date(timestamp * 1000));
    }
    /**
     *
     * @comment 时间戳转时间
     * @param timestamp
     * @return
     * 2015年12月18日
     * 下午6:28:13
     */
    public static Date timestampToDate(long timestamp){
        return new Date(timestamp * 1000);
    }
    /**
     *
     * @comment 获取10位时间戳
     * @return
     * 2015年12月18日
     * 下午6:29:48
     */
    public static long getTimestamp(){
        return System.currentTimeMillis() / 1000;
    }
    public static long getElevenTimestamp(){
        return System.currentTimeMillis();
    }

    /**
     * @comment 获取多少天前 或多少天后的日期 正数是当前日期加 负数是当前日期减
     * @param a
     * @return
     * 2016年2月24日
     * 上午9:42:01
     */
    public static Date getAddOrSubtractDate(int a){
        Date nowDate=new Date();
        return new Date(nowDate.getTime()+(a*24*60*60*1000));
    }
    /**
     *
     * @comment 获取当前日期
     * @param currentDate
     * @return
     * 2016年2月27日
     * 下午1:12:44
     */
    public static int getWeek(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return w;
    }

    public static String getMondayOfThisWeek() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return sdf.format(c.getTime())+" 08:00";
    }

    public static String getMondayOfNextWeek() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 8);
        return sdf.format(c.getTime())+" 08:00";
    }


    public static long dateStringTotimestamp(String date){
        Date d=null;
        try {
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat(PATTERN_DATE);
            d = simpleDateFormat .parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timeStemp = d.getTime();
        return timeStemp;
    }

    public static Map<String,Object> getlastWeek(){
        Map<String,Object> dateMap = new HashMap<String,Object>();
        Calendar cal = Calendar.getInstance();
        String monday;
        String sunday;
        //推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
        cal.add(Calendar.DATE, -1*7);
        //想周几，这里就传几Calendar.MONDAY（TUESDAY...）
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        monday = new SimpleDateFormat(PATTERN_DATE).format(cal.getTime());
        monday+=" 00:00:00";
        cal.add(Calendar.DATE, 1*7);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        sunday = new SimpleDateFormat(PATTERN_DATE).format(cal.getTime());
        sunday+=" 23:59:59";
        dateMap.put("startDate", monday);
        dateMap.put("endDate", sunday);
        return dateMap;
    }

    /**
     * 日期比较
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2){
        DateFormat df = new SimpleDateFormat(PATTERN_DATE);
        String d1 = df.format(date1);
        String d2 = df.format(date2);
        return d1.compareTo(d2);
    }

    /**
     * 处理日期数字，用":"，"-"等分隔。注：不能以0开头
     * @param date
     * @return
     */
    public static String divideDate(Long date) {
        if (null != date) {
            StringBuilder oriDate = new StringBuilder(String.valueOf(date));
            int length = oriDate.length();
            if (length > 14) { //带毫秒，先去年毫秒
                StringBuilder millDate = new StringBuilder(oriDate.substring(0, length - 3));
                length = millDate.length();
            }
            if (length > 10 && length < 15) {//带年月  2017-07-13 16：56：27
                StringBuilder hasYearDate = new StringBuilder(oriDate.substring(0, length - 6));//分成两部分，分别插入不同的分割符
                StringBuilder anotherDate = new StringBuilder(oriDate.substring(8, length));//时分秒
                for (int i = 4; i < hasYearDate.length(); i +=3) {
                    hasYearDate.insert(i, "-");
                }
                for (int i = 2; i < anotherDate.length(); i +=3) {
                    anotherDate.insert(i, ":");
                }
                return hasYearDate.append(" " + anotherDate).toString();
            } else {
                if (length%2 != 0) {//如果是奇数的话先补0
                    oriDate.insert(0, "0");
                }
                for (int i = 2; i < oriDate.length(); i +=3) {
                    oriDate.insert(i, ":");
                }
                return oriDate.toString();
            }
        }
        return null;
    }

    /**
     * 去除日期字符串中的"-",":" 空格等
     * @param dateString
     * @return
     */
    public static long removeDateSymbol(String dateString) {
        if (StringUtils.isNoneEmpty(dateString)) {
            return Long.parseLong(dateString.replaceAll("[\\pP\\pS\\pZ\\pC]", ""));
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.printf(divideDate(20170721162505744L));
    }
}
