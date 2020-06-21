package com.boardgamesworld.bgrental.repositories;

import com.boardgamesworld.bgrental.model.RentHistory;

import java.util.List;


public interface RentHistoryRepository {


    void addRentHistory(RentHistory rentHistory);

    List<Long> getAllBoardGameIdsRentHistoryByUser(long userId);

}
