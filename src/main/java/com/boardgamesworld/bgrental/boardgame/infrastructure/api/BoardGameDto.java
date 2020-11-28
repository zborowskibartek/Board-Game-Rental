package com.boardgamesworld.bgrental.boardgame.infrastructure.api;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGameCondition;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardGameDto {

    private long boardGameId;
    private String name;
    private double pricePerDay;
    private boolean rented;
    private BoardGameCondition condition;
    private BoardGameDetails details;

}
