package com.dendy.countinout.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class DateUtils {
    public static Timestamp getTimeSql(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
    public static Timestamp toStartOfDay(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalDateTime startOfDay = localDate.atStartOfDay();
        return Timestamp.valueOf(startOfDay);
    }
    public static Timestamp toEndOfDay(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX);
        return Timestamp.valueOf(endOfDay);
    }
}
