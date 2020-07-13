package com.boardgamesworld.bgrental.rent.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RentFacade {

    private RentService rentService;

    void rentBoardGame(long boardGameId, long userId) {
        rentService.rentBoardGame(boardGameId, userId);
    }

    void returnBoardGame(long boardGameId) {
        rentService.returnBoardGame(boardGameId);
    }

}
