package com.boardgamesworld.bgrental.sort.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BoardGameSortFacade {

    private BoardGameSortService boardGameSortService;

    public List<BoardGame> getSortedBoardGamesByName(String order) {
        return boardGameSortService.getSortedBoardGamesByName(order);
    }

    public List<BoardGame> getSortedBoardGamesByPrice(String order) {
        return boardGameSortService.getSortedBoardGamesByPrice(order);
    }

}
