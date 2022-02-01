package com.letscode.jogos.utils;

import com.letscode.jogos.model.Game;

import java.time.format.DateTimeFormatter;

public class DateTimeFormatter2 {

    public static String forString(Game game) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy, HH:mm");
        return dateTimeFormatter.format(game.getGameDateTime()).replace(":", "h");
    }
}
