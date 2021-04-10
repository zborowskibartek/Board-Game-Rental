package com.boardgamesworld.bgrental.rent.domain;

import java.util.List;

public interface RentRepository {

    void addRent(Rent rent);

    Rent getRent(long boardGameId);

    void removeRent(long boardGameId);

    List<Rent> getAllRents();

    List<Rent> getAllRentsByUserId(long userId);

    List<Long> getAllRentedBoardGameIds();

    List<Long> getAllRentBoardGameIdsByUser(long userId);

}
