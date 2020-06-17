package com.boardgamesworld.bgrental.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardGame {

    private long boardGameId;
    private String name;
    private int pricePerDay;
    private boolean rented = false;
    private BoardGameCondition condition;
    private BoardGameDetails details;

    public BoardGame() {
    }

    public BoardGame(long boardGameId,
                     String name,
                     int pricePerDay,
                     boolean rented,
                     BoardGameCondition condition,
                     BoardGameDetails details) {
        this.boardGameId = boardGameId;
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.rented = rented;
        this.condition = condition;
        this.details = details;
    }



    @Override
    public String toString() {
        return "BoardGame{" +
                "boardGameId=" + boardGameId +
                ", name='" + name + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", rented=" + rented +
                ", condition=" + condition +
                ", details=" + details +
                '}';
    }
}
