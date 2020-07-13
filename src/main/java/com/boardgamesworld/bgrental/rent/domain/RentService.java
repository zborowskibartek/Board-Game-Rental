package com.boardgamesworld.bgrental.rent.domain;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class RentService {


    private final RentRepository rentRepository;
    private final RentValidator rentValidator;


    void rentBoardGame(long boardGameId, long userId) {
        rentValidator.validateRent(boardGameId, userId);
        rentRepository.addRent(new Rent(userId, boardGameId, LocalDateTime.now(), null));
    }

    void returnBoardGame(long boardGameId, long userId) {
        rentRepository.getAllRentBoardGameIdsByUser(userId);
    }

}
