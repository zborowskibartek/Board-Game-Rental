package com.boardgamesworld.bgrental.repositories;

import java.util.List;

public interface RentRepository {

    List<Long> getAllBoardGameIdsRentAtPresent();

    List<Long> getAllBoardGameIdsRentByUser(long userId);

}
