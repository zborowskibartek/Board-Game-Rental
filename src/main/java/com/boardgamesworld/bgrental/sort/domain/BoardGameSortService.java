package com.boardgamesworld.bgrental.sort.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameFacade;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BoardGameSortService implements BoardGameSort {

    private final BoardGameFacade boardGameFacade;

    @Override
    public List<BoardGame> getSortedBoardGamesByName() {
        List<BoardGame> boardGames = boardGameFacade.getAllBoardGames();

        return boardGames.stream()
                .sorted(Comparator.comparing(BoardGame::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardGame> getSortedBoardGamesByPrice() {
        List<BoardGame> boardGames = boardGameFacade.getAllBoardGames();

        return boardGames.stream()
                .sorted(Comparator.comparing(BoardGame::getPricePerDay))
                .collect(Collectors.toList());
    }
}
