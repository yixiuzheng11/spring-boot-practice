package org.yixz.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年12月17日 17:14
 */
public class DateUtils {
    public static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String dateFormat = "yyyy-MM-dd";
    public static final ZoneId systemZone = ZoneId.systemDefault();
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat).withZone(systemZone);
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat).withZone(systemZone);

    /**
     * LocalDate转Date
     * @author YIXIUZHENG741
     * @date 2021/8/23 10:50
     * @param localDate
     * @return java.util.Date
     */
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(systemZone).toInstant());
    }

    /**
     * LocalDateTime转Date
     * @author YIXIUZHENG741
     * @date 2021/8/23 10:50
     * @param localDateTime
     * @return java.util.Date
     */
    public static Date asDateTime(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(systemZone).toInstant());
    }

    /**
     * Date转LocalDate
     * @author YIXIUZHENG741
     * @date 2021/8/23 10:51
     * @param date
     * @return java.time.LocalDate
     */
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(systemZone).toLocalDate();
    }

    /**
     * Date转LocalDateTime
     * @author YIXIUZHENG741
     * @date 2021/8/23 10:52
     * @param date
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(systemZone).toLocalDateTime();
    }

    /**
     * 将Date根据指定的日期进行格式化
     * @author YIXIUZHENG741
     * @date 2021/8/23 10:52
     * @param date
     * @param pattern
     * @return java.lang.String
     */
    public static String formatDate(Date date, String pattern){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern).withZone(systemZone);
        return dtf.format(date.toInstant());
    }

    /**
     * 将Date格式化，格式：yyyy-MM-dd
     * @author YIXIUZHENG741
     * @date 2021/8/23 10:52
     * @param date
     * @return java.lang.String
     */
    public static String formatDate(Date date){
        return dateFormatter.format(date.toInstant());
    }

    /**
     * 将Date格式化，格式：yyyy-MM-dd HH:mm:ss
     * @author YIXIUZHENG741
     * @date 2021/8/23 10:54
     * @param date
     * @return java.lang.String
     */
    public static String formatDateTime(Date date){
        return dateTimeFormatter.format(date.toInstant());
    }

    /**
     * 将Date根据指定的日期格式化
     * @author YIXIUZHENG741
     * @date 2021/8/23 10:54
     * @param date
     * @return java.lang.String
     */
    public static String formatDateTime(Date date, String pattern){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern).withZone(systemZone);
        return dtf.format(date.toInstant());
    }

    /**
     * 将格式为yyyy-MM-dd的字符串转换成Date
     * @author YIXIUZHENG741
     * @date 2021/8/23 10:55
     * @param str
     * @return java.util.Date
     */
    public static Date parseStrToDate(String str){
        LocalDate localDate = LocalDate.parse(str, dateFormatter);
        Instant instant = localDate.atStartOfDay().atZone(systemZone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * 将指定格式pattern的字符串转换成Date
     * @author YIXIUZHENG741
     * @date 2021/8/23 10:56
     * @param str
     * @param pattern
     * @return java.util.Date
     */
    public static Date parseStrToDate(String str, String pattern){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern).withZone(systemZone);
        LocalDate localDate = LocalDate.parse(str, dtf);
        Instant instant = localDate.atStartOfDay().atZone(systemZone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * 将格式为yyyy-MM-dd HH:mm:ss的字符串转换成Date
     * @author YIXIUZHENG741
     * @date 2021/8/23 10:55
     * @param str
     * @return java.util.Date
     */
    public static Date parseStrToDateTime(String str){
        LocalDateTime localDateTime = LocalDateTime.parse(str, dateTimeFormatter);
        Instant instant = localDateTime.atZone(systemZone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * 将指定格式pattern的字符串转换成Date
     * @author YIXIUZHENG741
     * @date 2021/8/23 10:59
     * @param str
     * @param pattern
     * @return java.util.Date
     */
    public static Date parseStrToDateTime(String str, String pattern){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern).withZone(systemZone);
        LocalDateTime localDateTime = LocalDateTime.parse(str, dtf);
        Instant instant = localDateTime.atZone(systemZone).toInstant();
        Date date = Date.from(instant);
        return date;
    }


    /**
     * 已给定日期为准，天数进行加减，返回日期，不包含时间
     * @author YIXIUZHENG741
     * @date 2021/8/11 19:42
     * @param days
     * @return java.lang.String
     */
    public static Date addDayForDate(Date date, int days) {
        LocalDate localDate = asLocalDate(date);
        localDate = localDate.plusDays(days);
        return asDate(localDate);
    }

    /**
     * 已当前日期为准，天数进行加减，返回日期时间
     * @author YIXIUZHENG741
     * @date 2021/8/11 19:42
     * @param days
     * @return java.lang.String
     */
    public static Date addDayForDateTime(Date date, int days) {
        LocalDateTime localDateTime = asLocalDateTime(date);
        localDateTime = localDateTime.plusDays(days);
        return asDateTime(localDateTime);
    }
}
