package com.boardgamesworld.bgrental.rent.infrastructure.database;

import com.boardgamesworld.bgrental.rent.domain.Rent;
import com.boardgamesworld.bgrental.rent.domain.RentRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
@Primary
public class PostgreSQLRentRepository implements RentRepository {

    private final RentRepositorySQL rentRepositorySQL;

    @Override
    public void addRent(Rent rent) {
        rentRepositorySQL.save(rent);
    }

    @Override
    public Rent getRent(long boardGameId) {
        return rentRepositorySQL.findByBoardGameId(boardGameId);
    }

    @Override
    public void removeRent(long boardGameId) {
        rentRepositorySQL.deleteByBoardGameId(boardGameId);
    }

    @Override
    public List<Rent> getAllRents() {
        return rentRepositorySQL.findAll();
    }

    @Override
    public List<Rent> getAllRentsByUserId(long userId) {
        return rentRepositorySQL.findAllByUserId(userId);
    }

    @Override
    public List<Long> getAllRentedBoardGameIds() {
        return rentRepositorySQL.findAll().stream()
                .map(Rent::getBoardGameId)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<Long> getAllRentBoardGameIdsByUser(long userId) {
        return rentRepositorySQL.findAll().stream()
                .filter(rent -> rent.getUserId() == userId)
                .map(Rent::getBoardGameId)
                .collect(Collectors.toList());
    }
}
