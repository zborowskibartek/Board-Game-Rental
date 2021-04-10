package com.boardgamesworld.bgrental.renthistory.infrastructure.database;

import com.boardgamesworld.bgrental.renthistory.domain.RentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface RentHistoryRepositorySQL extends JpaRepository<RentHistory, Long> {

    @Transactional
    List<RentHistory> findAllByUserId(long userId);
}
