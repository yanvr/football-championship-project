package com.letscode.jogos.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatter2 {

    public static String forString(LocalDate date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return dateTimeFormatter.format(date);
    }

    public static LocalDate ofString(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
