package com.boardgamesworld.bgrental.rent.domain;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RentRepositoryInMemory implements RentRepository {

    private final List<Rent> rents = new ArrayList<>();

    @Override
    public void addRent(Rent rent) {
        rents.add(rent);
    }

    public Rent getRent(long boardGameId) {
        return rents.stream()
                .filter(rent -> rent.getBoardGameId() == boardGameId)
                .findFirst()
                .get();
    }

    @Override
    public void removeRent(long boardGameId) {
        rents.removeIf(rent -> rent.getBoardGameId() == boardGameId);
    }

    @Override
    public List<Rent> getAllRents() {
        return rents;
    }

    @Override
    public List<Rent> getAllRentsByUserId(long userId) {
        return rents.stream()
                .filter(rent -> rent.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getAllRentedBoardGameIds() {
        return rents.stream()
                .map(Rent::getBoardGameId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getAllRentBoardGameIdsByUser(long userId) {
        return rents.stream()
                .filter(rent -> rent.getUserId() == userId)
                .map(Rent::getBoardGameId)
                .collect(Collectors.toList());
    }
}
