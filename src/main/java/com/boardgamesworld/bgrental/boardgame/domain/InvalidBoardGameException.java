package com.boardgamesworld.bgrental.boardgame.domain;

import java.util.List;

public class InvalidBoardGameException extends RuntimeException {

    private final List<String> boardGameErrors;

    public InvalidBoardGameException(List<String> boardGameErrors) {
        this.boardGameErrors = boardGameErrors;
    }

    public List<String> getBoardGameErrors() {
        return boardGameErrors;
    }
}
