package com.dendy.countinout.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static Timestamp getTimeSql() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public static Timestamp convertStringToTimeSql(String data) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date parsedDate = dateFormat.parse(data);
        return new Timestamp(parsedDate.getTime());
    }

    public static String convertDateTimeToTimeString(Timestamp data) throws ParseException {
        LocalDateTime localDateTime = data.toLocalDateTime();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String result = localDateTime.format(timeFormatter);
        return result;
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
