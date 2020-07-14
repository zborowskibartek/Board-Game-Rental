package com.boardgamesworld.bgrental.renthistory.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RentHistoryService {

    private final RentHistoryRepository rentHistoryRepository;

    void addRentHistory(RentHistory rentHistory){
        rentHistoryRepository.addRentHistory(rentHistory);
    }

    public List<RentHistory> getAllRentHistory() {
        return rentHistoryRepository.getAllRentHistory();
    }

    public List<RentHistory> getAllRentHistoryByUser(long userId) {
        return rentHistoryRepository.getAllRentHistoryByUser(userId);
    }
}