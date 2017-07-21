package com.xiaoshan.common.helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gukc007 on 2017-05-07.
 */
public class JavaHelper {

    public static Date localDateTimeToDate(LocalDateTime localDateTime) throws Exception {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) throws Exception {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static boolean isNullOrEmpty(String value){
        if (value == null) {
            return true;
        }
        String regEx = "^//s*$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }
}
