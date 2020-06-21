package com.boardgamesworld.bgrental.services;

import com.boardgamesworld.bgrental.model.BoardGame;
import com.boardgamesworld.bgrental.model.Rent;
import com.boardgamesworld.bgrental.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentService {

    private RentRepository rentRepository;
    private RentHistoryService rentHistoryService;
    private BoardGameService boardGameService;


    public RentService() {
    }

    @Autowired
    public RentService(RentRepository rentRepository, RentHistoryService rentHistoryService, BoardGameService boardGameService) {
        this.rentRepository = rentRepository;
        this.rentHistoryService = rentHistoryService;
        this.boardGameService = boardGameService;
    }

    public void rentBoardGame(long userId, long boardGameId) {
        changeBoardGameStatusAsRented(boardGameId);
        addRentToRepository(userId, boardGameId);
        createRentHistory(userId, boardGameId);
    }

    public void returnBoardGame(long userId, long boardGameId) {
        changeBoardGameStatusAsReturned(boardGameId);
        updateRentHistory(userId, boardGameId);
    }

    public List<BoardGame> getAllBoardGameRentAtPresentByUser(long userId){
        return rentRepository.getAllBoardGameIdsRentByUser(userId).stream()
                .map(boarGameId -> boardGameService.getBoardGame(boarGameId))
                .filter(BoardGame::isRented)
                .collect(Collectors.toList());
    }

    private void changeBoardGameStatusAsRented(long boardGameId) {
        BoardGame boardGame = boardGameService.getBoardGame(boardGameId);
        if (!checkBoardGameRentStatus(boardGame)) {
            changeBoardGameRentStatus(boardGame);
        } else {
            throw new IllegalStateException("Board game is already rented! Choose another one.");
        }
    }

    private boolean checkBoardGameRentStatus(BoardGame boardGame) {
        return boardGame.isRented();
    }

    private void changeBoardGameRentStatus(BoardGame boardGame) {
        boardGame.setRented(!boardGame.isRented());
    }

    private void addRentToRepository(long userId, long boardGameId) {
        rentRepository.addRent(createNewRent(userId, boardGameId));
    }

    private Rent createNewRent(long userId, long boardGameId) {
        boolean gameRentStatus = boardGameService.getBoardGame(boardGameId).isRented();

        return new Rent(userId, boardGameId, gameRentStatus);
    }

    private void createRentHistory(long userId, long boardGameId) {
        rentHistoryService.createRentHistory(userId, boardGameId);
    }

    private void changeBoardGameStatusAsReturned(long boardGameId) {
        BoardGame boardGame = boardGameService.getBoardGame(boardGameId);
        if (checkBoardGameRentStatus(boardGame)) {
            changeBoardGameRentStatus(boardGame);
        } else {
            throw new IllegalStateException("Board game is already returned! Choose another one.");
        }
    }

    private void updateRentHistory(long userId, long boardGameId) {
        rentHistoryService.updateRentHistory(userId,boardGameId);
    }
}

