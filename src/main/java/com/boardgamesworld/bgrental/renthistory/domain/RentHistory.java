package com.boardgamesworld.bgrental.renthistory.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter

@Entity
@Table(name = "rent_histories")
public class RentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long rentHistoryId;

    @Column(name = "user_id", nullable = false, updatable = false, columnDefinition = "DOUBLE PRECISION")
    private long userId;

    @Column(name = "board_game_id", nullable = false, updatable = false, columnDefinition = "DOUBLE PRECISION")
    private long boardGameId;

    @Column(name = "rented_date", nullable = false, updatable = false, columnDefinition = "DATE")
    private LocalDateTime rentedDate;

    @Column(name = "returned_date", nullable = false, updatable = false, columnDefinition = "DATE")
    private LocalDateTime returnedDate;

    public RentHistory(long userId, long boardGameId, LocalDateTime rentedDate, LocalDateTime returnedDate) {
        this.userId = userId;
        this.boardGameId = boardGameId;
        this.rentedDate = rentedDate;
        this.returnedDate = returnedDate;
    }
}
