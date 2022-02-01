package com.letscode.jogos.service;

import com.letscode.jogos.model.Game;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Core {

    public void run() {

        File file = new File("C:\\Users\\yanvr\\Desktop\\java-projects\\projeto-jogos\\src\\main\\java\\com\\letscode\\jogos\\files\\teste.csv");

        Set<Game> games = FileHandler.read(file);

        Map<String, List<Game>> gamesByTeam = games.stream()
                .collect(Collectors.groupingBy(game -> game.getHome().getName()));

        FileHandler.write(gamesByTeam);
    }
}
