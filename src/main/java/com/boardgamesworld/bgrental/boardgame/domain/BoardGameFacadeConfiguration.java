package com.boardgamesworld.bgrental.boardgame.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BoardGameFacadeConfiguration {

    private BoardGameFacade boardGameFacade;

    @Bean
    BoardGameFacade boardGameFacade(BoardGameRepository boardGameRepository) {
        BoardGameValidator boardGameValidator = new BoardGameValidator();
        BoardGameService boardGameService = new BoardGameService(boardGameRepository, boardGameValidator);

        return new BoardGameFacade(boardGameService);
    }

}
