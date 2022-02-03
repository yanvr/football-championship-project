package com.letscode.jogos.utils;

import com.letscode.jogos.model.Game;

import java.time.format.DateTimeFormatter;

public class DateTimeFormatter2 {

    public static String forString(Game game) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return dateTimeFormatter.format(game.getGameDate());
    }

    public static DateTimeFormatter toString(String date) {
        return DateTimeFormatter.ofPattern("dd/MM/yy");
    }
}
