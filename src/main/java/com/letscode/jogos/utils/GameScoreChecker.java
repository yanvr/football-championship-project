package com.letscode.jogos.utils;

import com.letscode.jogos.model.Game;
import com.letscode.jogos.model.Team;

public class GameScoreChecker {

    public static void check(Game game, Team team) {
        final int win = 3;
        final int draw = 1;

        if (game.getHomeGoals() > game.getVisitorGoals()) {
            team.setWins(team.getWins() + 1);
            team.setScore(team.getScore() + win);
        } else if (game.getHomeGoals() < game.getVisitorGoals()) {
            team.setLoses(team.getLoses() + 1);
        } else {
            team.setDraws(team.getDraws() + 1);
            team.setScore(team.getScore() + draw);
        }
    }
}
