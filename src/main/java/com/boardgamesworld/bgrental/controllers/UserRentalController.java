package com.boardgamesworld.bgrental.controllers;

import com.boardgamesworld.bgrental.services.UserRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRentalController {

    private UserRentalService userRentalService;

    @Autowired
    public UserRentalController(UserRentalService userRentalService) {
        this.userRentalService = userRentalService;
    }

    @PostMapping("/{userId}/rent/{boardGameId}")
    public void rentBoardGame(@PathVariable long userId, @PathVariable long boardGameId) {
        userRentalService.rentBoardGame(userId, boardGameId);
    }

    @PostMapping("/{userId}/return/{boardGameId}")
    public void returnBoardGame(@PathVariable long userId, @PathVariable long boardGameId) {
        userRentalService.returnBoardGame(userId, boardGameId);
    }
}
