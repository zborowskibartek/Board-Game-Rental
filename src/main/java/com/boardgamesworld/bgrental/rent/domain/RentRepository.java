package com.boardgamesworld.bgrental.rent.domain;

import java.util.List;

public interface RentRepository {

    void addRent(Rent rent);

    void removeRent(long boardGameId);

    List<Rent> getAllRents();

    List<Rent> getAllRentsByUser(long userId);

    List<Long> getAllRentBoardGameIds();

    List<Long> getAllRentBoardGameIdsByUser(long userId);

}
