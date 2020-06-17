package com.boardgamesworld.bgrental.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class User {

    private long userId;
    private String firstName;
    private String secondName;
    private String email;
    private String username;
    private String password;
    private List<BoardGame> atPresentRentedBoardGames;
    private List<BoardGame> rentedBoardGamesHistory;

    public User() {
    }

    public User(long userId,
                String firstName,
                String secondName,
                String email,
                String username,
                String password,
                List<BoardGame> atPresentRentedBoardGames,
                List<BoardGame> rentedBoardGamesHistory) {
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.atPresentRentedBoardGames = atPresentRentedBoardGames;
        this.rentedBoardGamesHistory = rentedBoardGamesHistory;
    }
}
