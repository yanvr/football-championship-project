package com.letscode.jogos.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Game implements Comparable<Game> {

    private Team home;
    private Team visitor;
    private int homeGoals;
    private int visitorGoals;
    private LocalDateTime gameDateTime;

    @Override
    public int compareTo(Game game) {
        int result = home.getName().compareTo(game.getHome().getName());

        if (result == 0) {
            result = gameDateTime.compareTo(game.getGameDateTime());
        }
        return result;
    }
}
