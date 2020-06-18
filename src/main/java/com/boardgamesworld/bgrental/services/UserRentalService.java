package com.boardgamesworld.bgrental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRentalService {


    private UserService userService;
    private BoardGameService boardGameService;


    public UserRentalService() {
    }

    @Autowired
    public UserRentalService(UserService userService, BoardGameService boardGameService) {
        this.userService = userService;
        this.boardGameService = boardGameService;
    }

    public void rentBoardGame(long userId, long boardGameId) {
        boardGameService.changeBoardGameStatusAsRented(boardGameId);
        userService.addBoardGameToUserAtPresentRentedList(userId, boardGameId);
    }

    public void returnBoardGame(long userId, long boardGameId) {
        boardGameService.changeBoardGameStatusAsReturned(boardGameId);
        userService.removeBoardGameFromUserAtPresentRentedList(userId, boardGameId);
        userService.addBoardGameToUserRentedHistory(userId, boardGameId);
    }

}

