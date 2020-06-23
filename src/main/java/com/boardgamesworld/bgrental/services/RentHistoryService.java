package com.boardgamesworld.bgrental.services;

import com.boardgamesworld.bgrental.model.BoardGame;
import com.boardgamesworld.bgrental.model.RentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentHistoryService {


    private RentHistoryRepository rentHistoryRepository;
    private BoardGameService boardGameService;

    public RentHistoryService() {
    }

    @Autowired
    public RentHistoryService(RentHistoryRepository rentHistoryRepository, BoardGameService boardGameService) {
        this.rentHistoryRepository = rentHistoryRepository;
        this.boardGameService = boardGameService;
    }


    public List<BoardGame> getAllBoardGameRentHistory() {
        return rentHistoryRepository.getAllBoardGameIdsRentHistory().stream()
                .map(boardGameId -> boardGameService.getBoardGame(boardGameId))
                .collect(Collectors.toList());
    }

    public List<BoardGame> getAllBoardGameRentHistoryByUser(long userId) {
        return rentHistoryRepository.getAllBoardGameIdsRentHistoryByUser(userId).stream()
                .map(boardGameId -> boardGameService.getBoardGame(boardGameId))
                .collect(Collectors.toList());
    }


    void createRentHistory(long userId, long boardGameId) {
        rentHistoryRepository.addRentHistory(new RentHistory(userId, boardGameId, LocalDateTime.now()));
    }

    void updateRentHistory(long userId, long boardGameId) {
        rentHistoryRepository.getRentHistoryByUserAndBoardGame(userId, boardGameId);
        // todo
    }
}
