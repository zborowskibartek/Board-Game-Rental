package com.boardgamesworld.bgrental.renthistory.domain;

import java.util.List;

public interface RentHistoryRepository {

    void addRentHistory(RentHistory rentHistory);

    List<RentHistory> getAllRentHistory();

    List<RentHistory> getAllRentHistoryByUser(long userId);
}
