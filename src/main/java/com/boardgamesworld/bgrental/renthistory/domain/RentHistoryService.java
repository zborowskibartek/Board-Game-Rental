package com.boardgamesworld.bgrental.renthistory.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class RentHistoryService {

    private final RentHistoryRepository rentHistoryRepository;

    void addRentHistory(RentHistory rentHistory) {
        rentHistoryRepository.addRentHistory(rentHistory);
    }

    List<RentHistory> getAllRentHistory() {
        return rentHistoryRepository.getAllRentHistory();
    }

    List<RentHistory> getAllRentHistoryByUser(long userId) {
        return rentHistoryRepository.getAllRentHistoryByUser(userId);
    }
}