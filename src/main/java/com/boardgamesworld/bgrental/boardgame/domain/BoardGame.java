package com.boardgamesworld.bgrental.boardgame.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardGame {

    private final long boardGameId;
    private final String name;
    private final double pricePerDay;
    private final boolean rented;
    private final BoardGameCondition condition;
    private final BoardGameDetails details;

}
