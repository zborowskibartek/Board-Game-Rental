package com.boardgamesworld.bgrental.renthistory.domain;

import com.boardgamesworld.bgrental.renthistory.infrastructure.database.inmemory.RentHistoryRepositoryInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentHistoryFacadeConfiguration {

    public RentHistoryFacade rentHistoryFacade() {
        return new RentHistoryFacadeConfiguration().rentHistoryFacade(new RentHistoryRepositoryInMemory());
    }

    @Bean
    RentHistoryFacade rentHistoryFacade(RentHistoryRepository rentHistoryRepository) {
        RentHistoryService rentHistoryService = new RentHistoryService(rentHistoryRepository);
        return new RentHistoryFacade(rentHistoryService);
    }
}
