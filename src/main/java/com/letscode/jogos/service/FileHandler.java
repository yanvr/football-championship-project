package com.letscode.jogos.service;

import com.google.common.base.Splitter;
import com.letscode.jogos.model.Game;
import com.letscode.jogos.model.Team;

import java.io.*;
import java.util.*;

import static com.letscode.jogos.utils.DateTimeFormatter2.forString;
import static com.letscode.jogos.utils.DateTimeFormatter2.ofString;

public class FileHandler {

    public static Set<Game> read(File file) {
        Set<Game> games = new TreeSet<>();

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while (bufferedReader.ready()) {
                String fileLine = bufferedReader.readLine();
                List<String> datas = Splitter.on(";")
                        .omitEmptyStrings()
                        .trimResults()
                        .splitToList(fileLine);

                Game game = Game.builder()
                        .homeTeam(datas.get(0))
                        .visitorTeam(datas.get(1))
                        .homeGoals(Integer.parseInt(datas.get(2)))
                        .visitorGoals(Integer.parseInt(datas.get(3)))
                        .gameDate(ofString(datas.get(4)))
                        .build();

                games.add(game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return games;
    }

    private File createFile(String fileName) {
        String dirName = "C:\\Users\\yanvr\\Desktop\\java-projects\\projeto-jogos\\src\\main\\java\\com\\letscode\\jogos\\files\\";
        File file = new File(dirName + fileName);

        try {
            if (file.createNewFile()) {
                System.out.printf("The file %s was created with success!!!%n", fileName);
                writeFileHeader(file);
            } else {
                System.out.printf("The file %s already created!!!%n", fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void writeTeamGames(Map<String, List<Game>> gamesByTeam) {

        List<Team> teams = new ArrayList<>();

        gamesByTeam.forEach((teamName, games) -> {
            File file = createFile(teamName.replace(" ", "-") + ".txt");

            Team team = new Team(teamName);

            games.forEach(game -> {
                try (FileWriter fileWriter = new FileWriter(file, true);
                     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                    bufferedWriter.write(forString(game.getGameDate()) + ":" + game.getHomeTeam()
                            + " " + game.getHomeGoals() + " x " + game.getVisitorGoals() + " " + game.getVisitorTeam());
                    bufferedWriter.newLine();

                    game.addHomeTeamScore(team);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            teams.add(team);
        });
        writeLeagueTable(teams);
    }

    private void writeLeagueTable(List<Team> teams) {
        teams.sort(Comparator.comparing(Team::getScore, Comparator.reverseOrder())
                .thenComparing(Team::getWins, Comparator.reverseOrder())
                .thenComparing(Team::getDraws));

        File file = createFile("league-table.csv");

        teams.forEach(team -> {
            try (FileWriter fileWriter = new FileWriter(file, true);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                bufferedWriter.write(team.getName() + ";" + team.getWins() + ";" + team.getDraws() + ";"
                        + team.getLoses() + ";" + team.getScore());
                bufferedWriter.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void writeFileHeader(File file) {
        if (file.getName().endsWith(".txt")) {
            String teamName = file.getName().replace(".txt", "").toUpperCase(Locale.ROOT);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))
            ) {
                bufferedWriter.write("######### " + teamName + " ##########");
                bufferedWriter.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                bufferedWriter.write("Time;Vitorias;Empates;Derrotas;Pontos");
                bufferedWriter.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
