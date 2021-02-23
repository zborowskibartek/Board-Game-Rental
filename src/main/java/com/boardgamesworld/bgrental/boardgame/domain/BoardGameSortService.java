package com.boardgamesworld.bgrental.boardgame.domain;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BoardGameSortService implements BoardGameSort {

    private final BoardGameRepository boardGameRepository;

    @Override
    public List<BoardGame> getAllSortedBoardGames(@Nullable BoardGameSortType sort, @Nullable Set<BoardGameType> types, @Nullable Set<BoardGameCategory> categories,
                                               @Nullable int offset, @Nullable int limit) {
        BoardGameSortType sortType = sort == null ? BoardGameSortType.NAME_ASC : sort;
        List<BoardGame> boardGames = boardGameRepository.getAllBoardGames();
        return boardGames.stream()
                .filter(boardGame -> hasAnyType(boardGame, types))
                .filter(boardGame -> hasAnyCategory(boardGame, categories))
                .sorted(getComparator(sortType))
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }

    private boolean hasAnyType(BoardGame boardGame, Set<BoardGameType> types) {
        if (types == null) {
            return true;
        } else {
            return boardGame.getDetails().getTypes().containsAll(types);
        }
    }

    private boolean hasAnyCategory(BoardGame boardGame, Set<BoardGameCategory> categories) {
        if (categories == null) {
            return true;
        } else {
            return boardGame.getDetails().getCategories().containsAll(categories);
        }
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
                StringBuilder availableSortType = new StringBuilder();
                for (BoardGameSortType type : BoardGameSortType.values()) {
                    availableSortType.append(" ").append(type.toString());
                }
                throw new InvalidSortTypeException("Invalid sort parameter. Available values: " + availableSortType);
        }
    }

}
