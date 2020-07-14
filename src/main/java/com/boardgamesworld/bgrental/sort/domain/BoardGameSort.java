package com.boardgamesworld.bgrental.sort.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;

import java.util.List;

public interface BoardGameSort {

    List<BoardGame> getSortedBoardGamesByName(String order);

    List<BoardGame> getSortedBoardGamesByPrice(String order);

}
