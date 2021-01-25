package com.boardgamesworld.bgrental.renthistory.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RentHistoryFacade {

    private RentHistoryService rentHistoryService;

    public void addRentHistory(RentHistory rentHistory) {
        rentHistoryService.addRentHistory(rentHistory);
    }

    public List<RentHistory> getAllRentHistory() {
        return rentHistoryService.getAllRentHistory();
    }

    public List<RentHistory> getAllRentHistoryByUser(long userId) {
        return rentHistoryService.getAllRentHistoryByUser(userId);
    }
}
