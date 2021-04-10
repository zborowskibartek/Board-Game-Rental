package com.boardgamesworld.bgrental.rent.infrastructure.database;

import com.boardgamesworld.bgrental.rent.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

interface RentRepositorySQL extends JpaRepository<Rent, Long> {

    @Transactional
    List<Rent> findAllByUserId(long userId);

    @Transactional
    Rent findByBoardGameId(long boardGameId);

    @Transactional
    void deleteByBoardGameId(long boardGameId);
}
