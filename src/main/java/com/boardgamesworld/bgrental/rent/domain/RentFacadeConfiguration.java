package com.boardgamesworld.bgrental.rent.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGameFacade;
import com.boardgamesworld.bgrental.renthistory.domain.RentHistoryFacade;
import com.boardgamesworld.bgrental.user.domain.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentFacadeConfiguration {

    @Bean
    RentFacade rentFacade(RentRepository rentRepository,
                          BoardGameFacade boardGameFacade,
                          UserFacade userFacade,
                          RentHistoryFacade rentHistoryFacade) {
        RentValidator rentValidator = new RentValidator(boardGameFacade, userFacade);
        RentService rentService = new RentService(rentRepository, rentValidator, boardGameFacade, rentHistoryFacade);

        return new RentFacade(rentService);
    }

}