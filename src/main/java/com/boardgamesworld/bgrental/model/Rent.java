package com.boardgamesworld.bgrental.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rent {

    private long userId;
    private long gameId;
    private boolean gameRentStatus;

    public Rent() {
    }

    public Rent(long userId, long gameId, boolean gameRentStatus) {
        this.userId = userId;
        this.gameId = gameId;
        this.gameRentStatus = gameRentStatus;
    }
}
