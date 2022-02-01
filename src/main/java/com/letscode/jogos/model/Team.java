package com.letscode.jogos.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Team {

    @NonNull private String name;
    private int wins;
    private int draws;
    private int loses;
    private int score;

}
