package com.gradel.common.shared.util;


import com.gradel.common.shared.constants.common.AppConstants;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Date;


/**
 * @description: java8日期工具
 * @author: sdeven.chen.dongwei@gmail.com
 * @date: 2019/7/18 15:19
 * @version: 1.0
 **/
public class LocalDateTimeUtil {

   /**
    * 日期格式yyyy-MM-dd
    */
   public static String DATE_PATTERN = "yyyy-MM-dd";

   /**
    * 小时：分钟
    */
   public final static String HOURSMIN_DATE_FORMAT = "HH:mm";

   /**
    * 日期时间格式yyyy-MM-dd HH:mm:ss
    */
   private final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

   private LocalDateTimeUtil() {

   }

   /**
    * Date转LocalDateTime
    *
    * @param date
    *            Date对象
    * @return LocalDateTime
    */
   public static LocalDateTime dateToLocalDateTime(Date date) {
       return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
   }

   /**
    * LocalDateTime转换为Date
    *
    * @param dateTime
    *            LocalDateTime对象
    * @return Date
    */
   public static Date localDateTimeToDate(LocalDateTime dateTime) {
       return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
   }

   /**
    * 格式化时间-默认yyyy-MM-dd HH:mm:ss格式
    *
    * @param dateTime
    *            LocalDateTime对象
    * @return String
    */
   public static String formatDateTime(LocalDateTime dateTime) {
       return formatDateTime(dateTime, DATE_TIME_PATTERN);
   }

   /**
    * 按pattern格式化时间-默认yyyy-MM-dd HH:mm:ss格式
    *
    * @param dateTime
    *            LocalDateTime对象
    * @param pattern
    *            要格式化的字符串
    * @return String
    */
   public static String formatDateTime(LocalDateTime dateTime, String pattern) {
       if (dateTime == null) {
           return null;
       }
       if (pattern == null || pattern.isEmpty()) {
           pattern = DATE_TIME_PATTERN;
       }
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
       return dateTime.format(formatter);
   }

   /**
    * 获取今天的00:00:00
    *
    * @return String
    */
   public static String getDayStart() {
       return getDayStart(LocalDateTime.now());
   }

   /**
    * 获取今天的23:59:59
    *
    * @return String
    */
   public static String getDayEnd() {
       return getDayEnd(LocalDateTime.now());
   }

   /**
    * 获取某天的00:00:00
    *
    * @param dateTime
    * @return String
    */
   public static String getDayStart(LocalDateTime dateTime) {
       return formatDateTime(dateTime.with(LocalTime.MIN));
   }

   /**
    * 获取某天的23:59:59
    *
    * @param dateTime
    * @return String
    */
   public static String getDayEnd(LocalDateTime dateTime) {
       return formatDateTime(dateTime.with(LocalTime.MAX));
   }

   /**
    * 获取本月第一天的00:00:00
    *
    * @return String
    */
   public static String getFirstDayOfMonth() {
       return getFirstDayOfMonth(LocalDateTime.now());
   }

   /**
    * 获取本月最后一天的23:59:59
    *
    * @return String
    */
   public static String getLastDayOfMonth() {
       return getLastDayOfMonth(LocalDateTime.now());
   }

   /**
    * 获取某月第一天的00:00:00
    *
    * @param dateTime
    *            LocalDateTime对象
    * @return String
    */
   public static String getFirstDayOfMonth(LocalDateTime dateTime) {
       return formatDateTime(dateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));
   }

   /**
    * 获取某月最后一天的23:59:59
    *
    * @param dateTime
    *            LocalDateTime对象
    * @return
    */
   public static String getLastDayOfMonth(LocalDateTime dateTime) {
       return formatDateTime(dateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX));
   }

   /**
    * Date转换为LocalDateTime.
    *
    * @param date
    * @return java.time.LocalDateTime
    */
   public static LocalDateTime convertDateToLDT(Date date) {
       return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
   }

   /**
    * Date转换为LocalDateTime. 东8区
    *
    * @param date
    * @return java.time.LocalDateTime
    */
   public static LocalDateTime dateToLocalTimeZoneIdSet8(Date date) {
       return LocalDateTime.ofInstant(date.toInstant(), ZoneId.of(AppConstants.ZONE_SET_8));
   }
   /**
    * LocalDateTime转换为LocalDateTime. 东8区
    * @return java.time.LocalDateTime
    */
   public static Date LocalDateTimeToZoneIdSet8() {
       return Date.from(LocalDateTime.now().atZone( ZoneId.of(AppConstants.ZONE_SET_8)).toInstant());
   }
   /**
    * LocalDateTime转换为LocalDateTime. 东8区
    * @return java.time.LocalDateTime
    */
   public static Long LocalDateTimeToZoneIdSet8Mill() {
       return LocalDateTime.now().atZone( ZoneId.of(AppConstants.ZONE_SET_8)).toInstant().toEpochMilli();
   }

   /**
    * LocalDateTime转换为Date.
    *
    * @param time
    * @return java.util.Date
    */
   public static Date convertLDTToDate(LocalDateTime time) {
       return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
   }


   /**
    * 获取指定日期的毫秒.
    *
    * @param time
    * @return java.lang.Long
    */
   public static Long getMilliByTime(LocalDateTime time) {
       return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
   }

   /**
    * 获取指定日期的秒.
    *
    * @param time
    * @return java.lang.Long
    */
   public static Long getSecondsByTime(LocalDateTime time) {
       return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
   }

   /**
    * 获取指定时间的指定格式.
    *
    * @param time
    * @param pattern
    * @return java.lang.String
    */
   public static String formatTime(LocalDateTime time, String pattern) {
       return time.format(DateTimeFormatter.ofPattern(pattern));
   }

   /**
    * 获取当前时间的指定格式.
    *
    * @param pattern
    * @return java.lang.String
    */
   public static String formatNow(String pattern) {
       return  formatTime(LocalDateTime.now(), pattern);
   }

   /**
    * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*.
    *
    * @param time
    * @param number
    * @param field
    * @return java.time.LocalDateTime
    */
   public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
       return time.plus(number, field);
   }

   /**
    * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*.
    *
    * @param time
    * @param number
    * @param field
    * @return java.time.LocalDateTime
    */
   public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
       return time.minus(number, field);
   }

   /**
    * 获取两个日期的差  field参数为ChronoUnit.*.
    *
    * @param startTime
    * @param endTime
    * @param field
    * @return long
    */
   public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
       Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
       if (field == ChronoUnit.YEARS) {
           return period.getYears();
       }
       if (field == ChronoUnit.MONTHS) {
           return period.getYears() * 12L + period.getMonths();
       }
       return field.between(startTime, endTime);
   }

   /**
    * 获取一天的开始时间，2017,7,22 00:00.
    *
    * @param time
    * @return java.time.LocalDateTime
    */
   public static LocalDateTime getDayStartTime(LocalDateTime time) {
       return time.withHour(0)
               .withMinute(0)
               .withSecond(0)
               .withNano(0);
   }

   /**
    * 获取一天的结束时间，2017,7,22 23:59:59.999999999.
    *
    * @param time
    * @return java.time.LocalDateTime
    */
   public static LocalDateTime getDayEndTime(LocalDateTime time) {
       return time.withHour(23)
               .withMinute(59)
               .withSecond(59)
               .withNano(999999999);
   }


   /**
    * 获取今天的00:00:00
    *
    * @return String
    */
   public static Date getDayStartDate() {
       ZoneId zoneId = ZoneId.systemDefault();
       ZonedDateTime zdt = LocalDateTime.now().with(LocalTime.MIN).atZone(zoneId);
       return Date.from(zdt.toInstant());
   }
   /**
    * 获取今天的23:59:59
    *
    * @return String
    */
   public static Date getDayEntDate() {
       ZoneId zoneId = ZoneId.systemDefault();
       ZonedDateTime zdt = LocalDateTime.now().with(LocalTime.MAX).atZone(zoneId);
       return Date.from(zdt.toInstant());
   }

   /**
    * 测试
    *
    * @param args
    */
   public static void main(String[] args) {
       System.out.println(getDayStart());
       System.out.println(getDayEnd());
       System.out.println(getFirstDayOfMonth());
       System.out.println(getLastDayOfMonth());
   }
   /**
    * 添加天数 的时间
    * @return String
    */
   public static Long addDayDate(int daySize) {
       ZoneId zoneId = ZoneId.systemDefault();
       return LocalDateTime.now().plusDays(daySize).atZone(zoneId).toInstant().toEpochMilli();
   }
   /**
    * 添加天数 的时间
    * @return String
    */
   public static Long addDayDate( Date time , int daySize) {
       ZoneId zoneId = ZoneId.systemDefault();
       return LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault()).plusDays(daySize).atZone(zoneId).toInstant().toEpochMilli();
   }

   /**
    * 添加天数 的时间
    * @return String
    */
   public static Long addDayDateMill(int daySize) {
       ZoneId zoneId = ZoneId.systemDefault();
       return LocalDateTime.now().plusDays(daySize).atZone(zoneId).toInstant().toEpochMilli();
   }
   /**
    * 格式化时间
    * @param date
    * @return
    */
   public static String formateDateStr(Date date) {
       if (date == null) {
           return null;
       }
       String str1 = "";
       try {
           str1 = new SimpleDateFormat(HOURSMIN_DATE_FORMAT).format(date);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return str1;
   }
}
