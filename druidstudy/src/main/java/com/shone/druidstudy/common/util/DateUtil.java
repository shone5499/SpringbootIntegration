package com.shone.druidstudy.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * 时间工具类
 * @author xiaoguojian
 */
public class DateUtil {

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_SPLIT_PATTERN = "yyyy-MM-dd";

    public static final String DATE_SIMPLE_TODAY_PATTERN = "yyyyMMdd";

    public static final String TIME_SPLIT_PATTERN = "HH:mm:ss";

    public static final String PATH_DATE_PATTERN = "yyyy/MM/dd";

    public static final String TIME_LAST_STR = " 23:59:59";

    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    public static String formatDate(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, DATE_SPLIT_PATTERN);
    }

    public static String formatDate(LocalDate localDate) {
        return formatDate(localDate, DATE_SPLIT_PATTERN);
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    public static String formatDate(LocalDate localDate, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(dateTimeFormatter);
    }

    public static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simformat = new SimpleDateFormat(dateFormatType);
        return simformat.format(date);
    }

    public static String formatCSTTime(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date d = sdf.parse(date);
        return DateUtil.getDateFormat(d, format);
    }

    /**
     * String to LocalDate
     * @param dateStr 20200102
     * @param pattern yyyyMMdd
     * @return LocalDate
     */
    public static LocalDate parseDate(String dateStr, String pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateStr,dateTimeFormatter);
    }

    /**
     * String to LocalDateTime
     * @param dateTimeStr 2020-12-12 01:01:20
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime(String dateTimeStr, String pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTimeStr,dateTimeFormatter);
    }

    /**
     * 获取一段时间的每一天日期
     *
     * @param start 2019-01-12
     * @param end 2019-03-01
     * @return 字符串数组
     */
    public static List<String> getBetweenDate(String start, String end) {
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> d.plusDays(1)).limit(distance + 1).forEach(f -> list.add(f.toString()));
        return list;
    }
}
