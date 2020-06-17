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

    void rentBoardGame(long customerId, long boardGameId) {
        userService.addBoardGameToUserAtPresentRentedList(customerId, boardGameId);
        boardGameService.changeBoardGameStatusAsRented(boardGameId);
    }

    void returnBoardGame(long customerId, long boardGameId) {
        userService.removeBoardGameFromUserAtPresentRentedList(customerId, boardGameId);
        userService.addBoardGameToUserRentedHistory(customerId, boardGameId);
        boardGameService.changeBoardGameStatusAsReturned(boardGameId);
    }

}
