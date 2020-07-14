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
    public List<BoardGame> getSortedBoardGamesByName(String order) {
        List<BoardGame> boardGames = boardGameFacade.getAllBoardGames();

        if (order.equals("asc")){
            return boardGames.stream()
                    .sorted(Comparator.comparing(BoardGame::getName))
                    .collect(Collectors.toList());
        } else if (order.equals("dsc")){
            return boardGames.stream()
                    .sorted(Comparator.comparing(BoardGame::getName).reversed())
                    .collect(Collectors.toList());
        } else {
            throw new InvalidSortException("Try with: \"asc\" or \"dsc\" as an order parameter!");
        }
    }

    @Override
    public List<BoardGame> getSortedBoardGamesByPrice(String order) {
        List<BoardGame> boardGames = boardGameFacade.getAllBoardGames();

        if (order.equals("asc")){
            return boardGames.stream()
                    .sorted(Comparator.comparing(BoardGame::getPricePerDay))
                    .collect(Collectors.toList());
        } else if (order.equals("dsc")){
            return boardGames.stream()
                    .sorted(Comparator.comparing(BoardGame::getPricePerDay).reversed())
                    .collect(Collectors.toList());
        } else {
            throw new InvalidSortException("Try with: \"asc\" or \"dsc\" as an order parameter!");
        }
    }
}
