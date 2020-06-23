package com.boardgamesworld.bgrental.repositories;

import com.boardgamesworld.bgrental.model.RentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Repository
public class RentHistoryRepositoryInMemory implements RentHistoryRepository {

    private List<RentHistory> rentHistory;

    @Autowired
    public RentHistoryRepositoryInMemory(List<RentHistory> rentHistory) {
        this.rentHistory = rentHistory;
    }

    @Override
    public void addRentHistory(RentHistory rentHistory) {
        this.rentHistory.add(rentHistory);
    }

    @Override
    public RentHistory getRentHistoryByUserAndBoardGame(long userId, long boardGameId) {
        return rentHistory.stream()
                .filter(rentHistory -> rentHistory.getUserId() == userId & rentHistory.getGameId() == boardGameId)
                .findFirst()
                .get();
    }

    @Override
    public List<Long> getAllBoardGameIdsRentHistory() {
        return rentHistory.stream()
                .map(RentHistory::getGameId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getAllBoardGameIdsRentHistoryByUser(long userId) {
        return rentHistory.stream()
                .filter(rentHistory -> rentHistory.getUserId() == userId)
                .map(RentHistory::getGameId)
                .collect(Collectors.toList());
    }
}

