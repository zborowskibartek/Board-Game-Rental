package com.boardgamesworld.bgrental.rent.domain;

import java.util.List;

public interface RentRepository {


    void addRent(Rent rent);

    List<Long> getAllRentBoardGameIds();

    List<Long> getAllRentBoardGameIdsByUser(long userId);

}
