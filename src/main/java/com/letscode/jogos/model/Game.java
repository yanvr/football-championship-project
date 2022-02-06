package com.letscode.jogos.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Game implements Comparable<Game> {

    private final int win = 3;
    private final int draw = 1;
    private String homeTeam;
    private String visitorTeam;
    private int homeGoals;
    private int visitorGoals;
    private LocalDate gameDate;

    public void isHomeOrVisitor(Team team) {
        if (team.getName().equals(homeTeam)) {
            addHomeScore(team);
        } else {
            addVisitorScore(team);
        }
    }


    private void addHomeScore(Team team) {
        if (homeGoals > visitorGoals) {
            team.setWins(team.getWins() + 1);
            team.setScore(team.getScore() + win);
        } else if (homeGoals < visitorGoals) {
            team.setLoses(team.getLoses() + 1);
        } else {
            team.setDraws(team.getDraws() + 1);
            team.setScore(team.getScore() + draw);
        }
    }

    private void addVisitorScore(Team team) {
        if (visitorGoals > homeGoals) {
            team.setWins(team.getWins() + 1);
            team.setScore(team.getScore() + win);
        } else if (visitorGoals < homeGoals) {
            team.setLoses(team.getLoses() + 1);
        } else {
            team.setDraws(team.getDraws() + 1);
            team.setScore(team.getScore() + draw);
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
