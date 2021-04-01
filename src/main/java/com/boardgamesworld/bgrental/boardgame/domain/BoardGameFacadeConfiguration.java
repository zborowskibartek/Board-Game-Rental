package com.boardgamesworld.bgrental.boardgame.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoardGameFacadeConfiguration {

    @Bean
    BoardGameFacade boardGameFacade(BoardGameRepository boardGameRepository) {
        BoardGameValidator boardGameValidator = new BoardGameValidator();
        BoardGameService boardGameService = new BoardGameService(boardGameRepository, boardGameValidator);
        BoardGameSortService boardGameSortService = new BoardGameSortService(boardGameRepository);
        return new BoardGameFacade(boardGameService, boardGameSortService);
    }
}
