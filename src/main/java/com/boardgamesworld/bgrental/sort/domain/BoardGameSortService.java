package com.boardgamesworld.bgrental.sort.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameCategory;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameFacade;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameType;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BoardGameSortService implements BoardGameSort {

    private final BoardGameFacade boardGameFacade;

    @Override
    public List<BoardGame> getSortedBoardGames(BoardGameSortType sort, List<BoardGameType> types, BoardGameCategory category) {
        BoardGameSortType sortType = sort == null ? BoardGameSortType.NAME_ASC : sort;
        List<BoardGame> boardGames = boardGameFacade.getAllBoardGames();

        return boardGames.stream()
                .sorted(getComparator(sortType))
                .filter(getFilterByType(types))
                .filter(getFilterByCategory(category))
                .collect(Collectors.toList());
    }

    private Predicate<BoardGame> getFilterByType(List<BoardGameType> types) {
        return types == null ? boardGame -> true : boardGame -> boardGame.getDetails().getTypes().containsAll(types);
    }

    private Predicate<BoardGame> getFilterByCategory(BoardGameCategory category) {
        return category == null ? boardGame -> true : boardGame -> boardGame.getDetails().getCategories().contains(category);
    }

    private Comparator<BoardGame> getComparator(BoardGameSortType sort) {
        Comparator<BoardGame> comparator;

        switch (sort) {
            case NAME_ASC:
                return comparator = Comparator.comparing(BoardGame::getName);
            case NAME_DSC:
                return comparator = Comparator.comparing(BoardGame::getName).reversed();
            case PRICE_ASC:
                return comparator = Comparator.comparing(BoardGame::getPricePerDay);
            case PRICE_DSC:
                return comparator = Comparator.comparing(BoardGame::getPricePerDay).reversed();
            default:
                throw new InvalidSortException("Try with: \"NAME_ASC\" or \"NAME_DSC\" or \"PRICE_ASC\" or \"PRICE_DSC\" as a sort parameter!");
                //wgl mi nie dociera do tego wyjątku, wcześniej wyskoczy ze nie udalo sie przekonwertowac stringa na istniejacego enuma ;(
        }
    }

}
