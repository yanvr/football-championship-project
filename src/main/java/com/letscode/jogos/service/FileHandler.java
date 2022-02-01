package com.letscode.jogos.service;

import com.letscode.jogos.model.Game;
import com.letscode.jogos.model.Team;
import com.letscode.jogos.utils.DateTimeFormatter2;
import com.letscode.jogos.utils.GameScoreChecker;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileHandler {

    public static Set<Game> read(File file) {
        Set<Game> games = new TreeSet<>();

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while (bufferedReader.ready()) {
                String fileLine = bufferedReader.readLine();
                String[] datas = fileLine.split(";");

                Game game = new Game();
                game.setHome(new Team(datas[0]));
                game.setVisitor(new Team(datas[1]));
                game.setHomeGoals(Integer.parseInt(datas[2]));
                game.setVisitorGoals(Integer.parseInt(datas[3]));
                game.setGameDateTime(LocalDateTime.parse(datas[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                games.add(game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return games;
    }

    public static void write(Map<String, List<Game>> gamesByTeam) {

        List<Team> teams = new ArrayList<>();

        for (Map.Entry<String, List<Game>> games : gamesByTeam.entrySet()) {

            String pathName = "C:\\Users\\yanvr\\Desktop\\java-projects\\projeto-jogos\\src\\main\\java\\com\\letscode\\jogos\\files\\"
                    + games.getKey().replace(" ", "-") + ".txt";

            File file = new File(pathName);

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Team team = new Team(games.getKey());

            for (Game game : games.getValue()) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                    bufferedWriter.write(DateTimeFormatter2.forString(game)
                            + ":" + game.getHome().getName() + " " + game.getHomeGoals()+ " x "
                            + game.getVisitorGoals() + " " + game.getVisitor().getName() + "\n");

                    GameScoreChecker.check(game, team);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            teams.add(team);
        }
        writeLeagueTable(teams);
    }

    private static void writeLeagueTable(List<Team> teams) {
        teams.sort(Comparator.comparing(Team::getScore, Comparator.reverseOrder()).thenComparing(Team::getWins));
        teams.forEach(System.out::println);

        File file = new File("C:\\Users\\yanvr\\Desktop\\java-projects\\projeto-jogos\\src\\main\\java\\com\\letscode\\jogos\\files\\"
                + "league-table.csv");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Team team : teams) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(team.getName() + ";" + team.getWins() + ";" + team.getDraws() + ";"
                + team.getLoses() + ";" + team.getScore());
                bufferedWriter.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}