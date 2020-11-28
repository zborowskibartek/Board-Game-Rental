package com.boardgamesworld.bgrental.sort.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameCategory;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameType;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BoardGameSortFacade {

    private BoardGameSortService boardGameSortService;

    public List<BoardGame> getSortedBoardGames(BoardGameSortType sort, List<BoardGameType> types, BoardGameCategory category) {
        return boardGameSortService.getSortedBoardGames(sort, types, category);
    }

}
