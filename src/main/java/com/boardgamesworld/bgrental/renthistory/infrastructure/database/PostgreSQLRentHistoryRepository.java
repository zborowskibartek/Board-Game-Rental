package com.boardgamesworld.bgrental.renthistory.infrastructure.database;

import com.boardgamesworld.bgrental.renthistory.domain.RentHistory;
import com.boardgamesworld.bgrental.renthistory.domain.RentHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
@Primary
public class PostgreSQLRentHistoryRepository implements RentHistoryRepository {

    private final RentHistoryRepositorySQL rentHistoryRepositorySQL;

    @Override
    public void addRentHistory(RentHistory rentHistory) {
        rentHistoryRepositorySQL.save(rentHistory);
    }

    @Override
    public List<RentHistory> getAllRentHistory() {
        return rentHistoryRepositorySQL.findAll();
    }

    @Override
    public List<RentHistory> getAllRentHistoryByUser(long userId) {
        return rentHistoryRepositorySQL.findAllByUserId(userId);
    }

}
