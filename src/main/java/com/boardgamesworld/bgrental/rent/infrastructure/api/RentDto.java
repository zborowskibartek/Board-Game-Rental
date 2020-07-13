package com.boardgamesworld.bgrental.rent.infrastructure.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {

    private long gameId;
    private long userId;

}