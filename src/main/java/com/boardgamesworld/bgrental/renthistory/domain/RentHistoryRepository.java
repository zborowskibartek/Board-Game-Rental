package com.boardgamesworld.bgrental.renthistory.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentHistoryRepository {

    void addRentHistory(RentHistory rentHistory);

    List<RentHistory> getAllRentHistory();

    List<RentHistory> getAllRentHistoryByUser(long userId);
}
