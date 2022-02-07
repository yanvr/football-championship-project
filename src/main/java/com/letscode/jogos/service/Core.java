package com.letscode.jogos.service;

import com.letscode.jogos.model.Game;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Core {

    public static void run() {

        String inputFile = "C:\\Users\\yanvr\\Desktop\\java-projects\\projeto-jogos\\src\\main\\resources\\santander811matchesResult.csv";

        File file = new File(inputFile);

        Set<Game> games = FileHandler.read(file);

        Map<String, List<Game>> homeTeamGames = games.stream()
                .collect(Collectors.groupingBy(Game::getHomeTeam));

        Map<String, List<Game>> visitorTeamGames = games.stream()
                .collect(Collectors.groupingBy(Game::getVisitorTeam));

        visitorTeamGames.forEach((key, value) -> value.sort(Comparator.comparing(Game::getGameDate)));

        Map<String, List<Game>> gamesPerTeam = Stream.of(homeTeamGames, visitorTeamGames)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Core::mergeGameList, HashMap::new));

        FileHandler fileHandler = new FileHandler();

        fileHandler.writeTeamGames(gamesPerTeam);
    }

    public static List<Game> mergeGameList(List<Game> game1, List<Game> game2) {
        game1.addAll(game2);
        return game1;
    }
}