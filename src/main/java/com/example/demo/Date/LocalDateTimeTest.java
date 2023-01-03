package com.example.demo.Date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Locale;

/**
 * @description:
 * @author: chenliang
 * @create: 2022-12-28 10:03
 **/
public class LocalDateTimeTest {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        System.out.println(localDateTime);
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE));
        System.out.println(localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CANADA)));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_WEEK_DATE));
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2022-12-28T11:04:25.08"));

        System.out.println(LocalDateTime.now().plusDays(1));
        System.out.println(LocalDateTime.now().plus(2, ChronoUnit.DAYS));
        System.out.println(LocalDateTime.now().plus(Period.ofDays(-2)));


    }
}