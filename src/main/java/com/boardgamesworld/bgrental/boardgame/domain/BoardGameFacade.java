package com.boardgamesworld.bgrental.boardgame.domain;

import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

public class BoardGameFacade {

    private final BoardGameService boardGameService;
    private final BoardGameSortService boardGameSortService;

    public BoardGameFacade(BoardGameService boardGameService, BoardGameSortService boardGameSortService) {
        this.boardGameService = boardGameService;
        this.boardGameSortService = boardGameSortService;
    }

    public List<BoardGame> getAllBoardGames() {
        return boardGameService.getAllBoardGames();
    }

    public List<BoardGame> getSortedBoardGames(@Nullable BoardGameSortType sort, @Nullable Set<BoardGameType> types, @Nullable Set<BoardGameCategory> categories,
                                               int offset, int limit) {
        return boardGameSortService.getSortedBoardGames(sort, types, categories, offset, limit);
    }

    public BoardGame getBoardGame(long boardGameId) {
        return boardGameService.getBoardGame(boardGameId);
    }

    public void addBoardGame(BoardGame boardGame) {
        boardGameService.addBoardGame(boardGame);
    }

    public void deleteBoardGame(long boardGameId) {
        boardGameService.deleteBoardGame(boardGameId);
    }

    public void updateBoardGame(long boardGameId, BoardGame updatedBoardGame) {
        boardGameService.updateBoardGame(boardGameId, updatedBoardGame);
    }

}
