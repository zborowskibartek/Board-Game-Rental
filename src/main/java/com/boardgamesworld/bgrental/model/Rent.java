package com.boardgamesworld.bgrental.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rent {

    private long userId;
    private long gameId;
    private boolean rented;

    public Rent() {
    }

    public Rent(long userId, long gameId, boolean rented) {
        this.userId = userId;
        this.gameId = gameId;
        this.rented = rented;
    }
}
