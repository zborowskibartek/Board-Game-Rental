package com.boardgamesworld.bgrental.boardgame.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BoardGame {

    private final long boardGameId;
    private final String name;
    private final double pricePerDay;
    private final boolean rented;
    private final BoardGameCondition condition;
    private final BoardGameDetails details;

}
