package com.boardgamesworld.bgrental.renthistory.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentHistoryDto {

    private long userId;
    private long gameId;
    private LocalDateTime rentedDate;
    private LocalDateTime returnedDate;

}
