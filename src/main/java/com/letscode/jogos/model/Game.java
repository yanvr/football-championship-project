package com.letscode.jogos.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@EqualsAndHashCode
public class Game implements Comparable<Game> {

    private String homeTeam;
    private String visitorTeam;
    private int homeGoals;
    private int visitorGoals;
    private LocalDate gameDate;

    public void addHomeTeamScore(Team home) {
        final int win = 3;
        final int draw = 1;

        if (homeGoals > visitorGoals) {
            home.setWins(home.getWins() + 1);
            home.setScore(home.getScore() + win);
        } else if (homeGoals < visitorGoals) {
            home.setLoses(home.getLoses() + 1);
        } else {
            home.setDraws(home.getDraws() + 1);
            home.setScore(home.getScore() + draw);
        }
    }

    @Override
    public int compareTo(Game game) {
        int result = homeTeam.compareTo(game.homeTeam);

        if (result == 0) {
            result = gameDate.compareTo(game.getGameDate());
        }
        return result;
    }
}
