package com.boardgamesworld.bgrental.renthistory.domain;

import java.util.List;

public class RentHistoryFacade {

    private RentHistoryService rentHistoryService;

    void addRentHistory(RentHistory rentHistory) {
        rentHistoryService.addRentHistory(rentHistory);
    }

    List<RentHistory> getAllRentHistory() {
        return rentHistoryService.getAllRentHistory();
    }

    List<RentHistory> getAllRentHistoryByUser(long userId) {
        return rentHistoryService.getAllRentHistoryByUser(userId);
    }
}
