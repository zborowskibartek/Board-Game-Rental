package com.boardgamesworld.bgrental.rent.infrastructure.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {

    private long userId;
    private long gameId;
    private LocalDateTime rentedDate;
    private LocalDateTime returnedDate;

}