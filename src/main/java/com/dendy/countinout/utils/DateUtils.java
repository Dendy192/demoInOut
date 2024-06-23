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
        dateFormat.setLenient(false);
        Date parsedDate = dateFormat.parse(data);
        return new Timestamp(parsedDate.getTime());
    }

    public static String convertDateTimeToTimeString(Timestamp data) throws ParseException {
        LocalDateTime localDateTime = data.toLocalDateTime();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        String result = localDateTime.format(timeFormatter);
        return result;
    }

    public static String convertDateTimeToTimeStringFull(Timestamp data) throws ParseException {
        LocalDateTime localDateTime = data.toLocalDateTime();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String result = localDateTime.format(timeFormatter);
        return result;
    }

    public static String convertDateTimeToTimeStringHalf(String data) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd MMM yyyy");
        Date parsedDate = dateFormat.parse(data);
        String result = dateFormat1.format(parsedDate);
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
        LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX).withNano(0);
        return Timestamp.valueOf(endOfDay);
    }

    public static String dateSqlToString(java.sql.Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        return dateFormat.format(date);
    }

    public static java.sql.Date stringToDateSQL(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd MMM yyyy");
        Date date1 = dateFormat1.parse(date);
        String formattedDateString = dateFormat.format(date1);
        return java.sql.Date.valueOf(formattedDateString);
    }
}
