package com.boardgamesworld.bgrental.renthistory.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class RentHistory {

    private final long userId;
    private final long gameId;
    private final LocalDateTime rentedDate;
    private final LocalDateTime returnedDate;

}
