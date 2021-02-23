package com.boardgamesworld.bgrental.boardgame.domain;

class BoardGameService {

    private final BoardGameRepository boardGameRepository;
    private final BoardGameValidator boardGameValidator;

    BoardGameService(BoardGameRepository boardGameRepository, BoardGameValidator boardGameValidator) {
        this.boardGameRepository = boardGameRepository;
        this.boardGameValidator = boardGameValidator;
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
