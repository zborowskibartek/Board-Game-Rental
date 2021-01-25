package com.boardgamesworld.bgrental.rent.infrastructure.database.inmemory;

import com.boardgamesworld.bgrental.rent.domain.InvalidRentException;
import com.boardgamesworld.bgrental.rent.domain.Rent;
import com.boardgamesworld.bgrental.rent.domain.RentRepository;
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

    public Rent getRent(long boardGameId){
        return rents.stream()
                .filter(rent -> rent.getGameId() == boardGameId )
                .findFirst()
                .get();
    }

    @Override
    public void removeRent(long boardGameId) {
        rents.removeIf(rent -> rent.getGameId() == boardGameId);
    }

    @Override
    public List<Rent> getAllRents() {
        return rents;
    }

    @Override
    public List<Rent> getAllRentsByUser(long userId) {
        return rents.stream()
                .filter(rent -> rent.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getAllRentBoardGameIds() {
        return rents.stream()
                .map(Rent::getGameId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getAllRentBoardGameIdsByUser(long userId) {
        return rents.stream()
                .filter(rent -> rent.getUserId() == userId)
                .map(Rent::getGameId)
                .collect(Collectors.toList());
    }

}
