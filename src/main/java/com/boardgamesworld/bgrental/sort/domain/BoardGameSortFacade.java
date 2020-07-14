package com.boardgamesworld.bgrental.sort.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BoardGameSortFacade {

    private BoardGameSortService boardGameSortService;

    public List<BoardGame> getSortedBoardGamesByName() {
        return boardGameSortService.getSortedBoardGamesByName();
    }

    public List<BoardGame> getSortedBoardGamesByPrice() {
        return boardGameSortService.getSortedBoardGamesByPrice();
    }

}
