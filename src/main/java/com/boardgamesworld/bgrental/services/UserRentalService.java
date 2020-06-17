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
        userService.addBoardGameToUserAtPresentRentedList(userId, boardGameId);
        boardGameService.changeBoardGameStatusAsRented(boardGameId);
    }

    public void returnBoardGame(long userId, long boardGameId) {
        userService.removeBoardGameFromUserAtPresentRentedList(userId, boardGameId);
        userService.addBoardGameToUserRentedHistory(userId, boardGameId);
        boardGameService.changeBoardGameStatusAsReturned(boardGameId);
    }

}

