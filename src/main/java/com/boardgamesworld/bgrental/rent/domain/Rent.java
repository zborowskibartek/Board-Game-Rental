package com.boardgamesworld.bgrental.rent.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Rent {

    private final long gameId;
    private final long userId;

}