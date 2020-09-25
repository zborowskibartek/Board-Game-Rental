package com.boardgamesworld.bgrental.rent.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Rent {

    private final long gameId;
    private final long userId;
    private final LocalDateTime rentedDate;


}