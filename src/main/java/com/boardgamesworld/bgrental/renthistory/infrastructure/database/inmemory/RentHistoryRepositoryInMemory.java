package com.boardgamesworld.bgrental.renthistory.infrastructure.database.inmemory;

import com.boardgamesworld.bgrental.renthistory.domain.RentHistory;
import com.boardgamesworld.bgrental.renthistory.domain.RentHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RentHistoryRepositoryInMemory implements RentHistoryRepository {

    private final List<RentHistory> rentHistories = new ArrayList<>();

    @Override
    public void addRentHistory(RentHistory rentHistory) {
        rentHistories.add(rentHistory);
    }

    @Override
    public List<RentHistory> getAllRentHistory() {
        return rentHistories;
    }

    @Override
    public List<RentHistory> getAllRentHistoryByUser(long userId) {
        return rentHistories.stream()
                .filter(rentHistory -> rentHistory.getUserId() == userId)
                .collect(Collectors.toList());
    }
}
