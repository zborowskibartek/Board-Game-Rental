package com.boardgamesworld.bgrental.boardgame.domain;

import java.util.List;

public class BoardGameFacade {

    private BoardGameService boardGameService;

    public BoardGameFacade(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    public List<BoardGame> getAllBoardGames() {
        return boardGameService.getAllBoardGames();
    }

    public BoardGame getBoardGame(long boardGameId) {
        return boardGameService.getBoardGame(boardGameId);
    }

    public void addBoardGame(BoardGame boardGame) {
        boardGameService.addBoardGame(boardGame);
    }

    public void updateBoardGame(long boardGameId, BoardGame updatedBoardGame) {
        boardGameService.updateBoardGame(boardGameId, updatedBoardGame);
    }

    public void deleteBoardGame(long boardGameId) {
        boardGameService.deleteBoardGame(boardGameId);
    }

}
