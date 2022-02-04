package com.letscode.jogos.model;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Team {

    @NonNull private String name;
    private int wins;
    private int draws;
    private int loses;
    private int score;

}
