package com.boardgamesworld.bgrental.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RentHistory {

    private long userId;
    private long gameId;
    private LocalDateTime rentedDate;
    private LocalDateTime returnedDate = null;

    public RentHistory() {
    }

    public RentHistory(long userId, long gameId, LocalDateTime rentedDate) {
        this.userId = userId;
        this.gameId = gameId;
        this.rentedDate = rentedDate;
    }
}
