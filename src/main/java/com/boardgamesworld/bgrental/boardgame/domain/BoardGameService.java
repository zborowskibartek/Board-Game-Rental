package com.boardgamesworld.bgrental.boardgame.domain;

import java.util.List;

class BoardGameService {

    private BoardGameRepository boardGameRepository;
    private BoardGameValidator boardGameValidator;

    BoardGameService(BoardGameRepository boardGameRepository, BoardGameValidator boardGameValidator) {
        this.boardGameRepository = boardGameRepository;
        this.boardGameValidator = boardGameValidator;
    }

    List<BoardGame> getAllBoardGames() {
        return boardGameRepository.getAllBoardGames();
    }

    BoardGame getBoardGame(long boardGameId) {
        return boardGameRepository.getBoardGame(boardGameId);
    }

    void addBoardGame(BoardGame boardGame) {
        boardGameValidator.validateBoardGame(boardGame);
        boardGameRepository.addBoardGame(boardGame);
    }

    void updateBoardGame(long boardGameId, BoardGame updatedBoardGame) {
        boardGameValidator.validateBoardGame(updatedBoardGame);
        boardGameRepository.updateBoardGame(boardGameId, updatedBoardGame);
    }

    void deleteBoardGame(long boardGameId) {
        boardGameRepository.deleteBoardGame(boardGameId);
    }

}
