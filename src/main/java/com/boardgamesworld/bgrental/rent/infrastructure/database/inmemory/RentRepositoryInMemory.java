package com.boardgamesworld.bgrental.rent.infrastructure.database.inmemory;

import com.boardgamesworld.bgrental.rent.domain.Rent;
import com.boardgamesworld.bgrental.rent.domain.RentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentRepositoryInMemory implements RentRepository {

    private final List<Rent> rents = new ArrayList<>();

    @Override
    public void addRent(Rent rent) {
        rents.add(rent);
    }

    @Override
    public List<Long> getAllRentBoardGameIds() {
        return rents.stream()
                .filter(rent -> rent.getReturnedDate() == null)
                .map(Rent::getGameId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getAllRentBoardGameIdsByUser(long userId) {
        return rents.stream()
                .filter(rent -> rent.getUserId() == userId && rent.getReturnedDate() == null)
                .map(Rent::getGameId)
                .collect(Collectors.toList());
    }

}
