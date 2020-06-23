package com.boardgamesworld.bgrental.controllers;

import com.boardgamesworld.bgrental.model.BoardGame;
import com.boardgamesworld.bgrental.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class RentController {

    private RentService userRentalService;

    @Autowired
    public RentController(RentService userRentalService) {
        this.userRentalService = userRentalService;
    }

    @PostMapping("/rent")
    public void rentBoardGame(@RequestParam long userId, @RequestParam long boardGameId) {
        userRentalService.rentBoardGame(userId, boardGameId);
    }

    @PostMapping("/return")
    public void returnBoardGame(@RequestParam long userId, @RequestParam long boardGameId) {
        userRentalService.returnBoardGame(userId, boardGameId);
    }

    @GetMapping("/rents")
    public List<BoardGame> getAllBoardGameRentAtPresent() {
        return userRentalService.getAllBoardGameRentAtPresent();
    }

    @GetMapping("/rents/{userId}")
    public List<BoardGame> getAllBoardGameRentAtPresentByUser(@PathVariable long userId) {
        return userRentalService.getAllBoardGameRentAtPresentByUser(userId);
    }
}
