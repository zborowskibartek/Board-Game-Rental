package com.boardgamesworld.bgrental.repositories;

import java.util.List;

public interface RentHistoryRepository {

    List<Long> getAllBoardGameRentHistoryByUser(long userId);

}
