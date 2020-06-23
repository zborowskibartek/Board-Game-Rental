package com.boardgamesworld.bgrental.repositories;

import com.boardgamesworld.bgrental.model.Rent;

import java.util.List;

public interface RentRepository {

    void addRent(Rent rent);

    List<Long> getAllBoardGameIdsRentAtPresent();

    List<Long> getAllBoardGameIdsRentAtPresentByUser(long userId);

}
