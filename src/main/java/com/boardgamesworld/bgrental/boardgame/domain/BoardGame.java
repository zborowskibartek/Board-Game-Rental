package com.boardgamesworld.bgrental.boardgame.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardGame {

    private long boardGameId;
    private String name;
    private double pricePerDay;
    private boolean rented = false;
    private BoardGameCondition condition;
    private BoardGameDetails details;
}
