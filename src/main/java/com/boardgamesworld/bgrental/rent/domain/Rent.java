package com.boardgamesworld.bgrental.rent.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rentId;

    @Column(name = "user_id", nullable = false, updatable = false, columnDefinition = "DOUBLE PRECISION")
    private long userId;

    @Column(name = "board_game_id", nullable = false, updatable = false, columnDefinition = "DOUBLE PRECISION")
    private long boardGameId;

    @Column(name = "rented_date", nullable = false, updatable = false, columnDefinition = "DATE")
    private LocalDateTime rentedDate;

    public Rent(long boardGameId, long userId, LocalDateTime rentedDate) {
        this.boardGameId = boardGameId;
        this.userId = userId;
        this.rentedDate = rentedDate;
    }
}