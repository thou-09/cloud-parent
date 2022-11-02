package com.itany.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * DateUtils.
 *
 * @author Thou
 * @date 2022/11/2
 */
public class DateUtils {

    /**
     * LocalDate 转 Date
     *
     * @param localDate -
     * @return java.util.Date
     * @author Thou
     * @date 2022/11/2
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atTime(LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDate
     *
     * @param date -
     * @return java.time.LocalDate
     * @author Thou
     * @date 2022/11/2
     */
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date 增加 day 天
     *
     * @param date -
     * @param day -
     * @return java.util.Date
     * @author Thou
     * @date 2022/11/2
     */
    public static Date dateAddDay(Date date, long day) {
        return localDateToDate(dateToLocalDate(date).plusDays(day));
    }
}
