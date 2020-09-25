package com.boardgamesworld.bgrental.rent.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RentFacade {

    private RentService rentService;

    public void rentBoardGame(long boardGameId, long userId) {
        rentService.rentBoardGame(boardGameId, userId);
    }

    public void returnBoardGame(long boardGameId) {
        rentService.returnBoardGame(boardGameId);
    }

    public List<Rent> getAllRents() {
        return rentService.getAllRents();
    }

    public List<Rent> getAllRentsByUser(long userId) {
        return rentService.getAllRentsByUser(userId);
    }

    public List<BoardGame> getAllRentBoardGames() {
        return rentService.getAllRentBoardGames();
    }

    public List<BoardGame> getAllRentBoardGamesByUser(long userId) {
        return rentService.getAllRentBoardGamesByUser(userId);
    }

}
