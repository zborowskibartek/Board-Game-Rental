package com.boardgamesworld.bgrental.services;

import com.boardgamesworld.bgrental.model.RentHistory;
import com.boardgamesworld.bgrental.repositories.RentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RentHistoryService {


    private RentHistoryRepository rentHistoryRepository;

    public RentHistoryService() {
    }

    @Autowired
    public RentHistoryService(RentHistoryRepository rentHistoryRepository) {
        this.rentHistoryRepository = rentHistoryRepository;
    }


    void createRentHistory(long userId, long boardGameId) {
        rentHistoryRepository.addRentHistory(new RentHistory(userId, boardGameId, LocalDateTime.now()));
    }

    void updateRentHistory(long userId, long boardGameId) {
        // todo
    }
}
