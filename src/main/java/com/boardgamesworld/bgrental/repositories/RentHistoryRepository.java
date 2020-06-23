package com.boardgamesworld.bgrental.repositories;

import com.boardgamesworld.bgrental.model.RentHistory;

import java.util.List;


public interface RentHistoryRepository {


    void addRentHistory(RentHistory rentHistory);

    RentHistory getRentHistoryByUserAndBoardGame(long userId, long boardGameId);

    List<Long> getAllBoardGameIdsRentHistory();

    List<Long> getAllBoardGameIdsRentHistoryByUser(long userId);

}
