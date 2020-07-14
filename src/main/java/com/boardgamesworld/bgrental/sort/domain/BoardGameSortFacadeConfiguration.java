package com.boardgamesworld.bgrental.sort.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGameFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoardGameSortFacadeConfiguration {

    @Bean
    public BoardGameSortFacade boardGameSortFacade(BoardGameFacade boardGameFacade){
        BoardGameSortService boardGameSortService = new BoardGameSortService(boardGameFacade);

        return new BoardGameSortFacade(boardGameSortService);
    }
}
