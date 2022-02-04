package com.letscode.jogos.service;

import com.letscode.jogos.model.Game;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Core {

    public static void run() {

        String inputFile = "C:\\Users\\yanvr\\Desktop\\java-projects\\projeto-jogos\\src\\main\\java\\com\\letscode\\" +
                "jogos\\files\\brasileirao2020.csv";

        File file = new File(inputFile);

        Set<Game> games = FileHandler.read(file);

        Map<String, List<Game>> teamByGames = games.stream()
                .collect(Collectors.groupingBy(Game::getHomeTeam));

        FileHandler fileHandler = new FileHandler();
//
        fileHandler.writeTeamGames(teamByGames);

    }
}
