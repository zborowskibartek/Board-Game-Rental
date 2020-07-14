package com.boardgamesworld.bgrental.sort.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;

import java.util.List;

public interface BoardGameSort {

    List<BoardGame> getSortedBoardGamesByName(List<BoardGame> boardGames);

    List<BoardGame> getSortedBoardGamesByPrice(List<BoardGame> boardGames);

}
