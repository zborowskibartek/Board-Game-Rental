package com.boardgamesworld.bgrental.sort.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameCategory;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameType;

import java.util.List;

public interface BoardGameSort {

    List<BoardGame> getSortedBoardGames(BoardGameSortType sort, List<BoardGameType> types, BoardGameCategory category);

}
