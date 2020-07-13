package com.boardgamesworld.bgrental.boardgame.domain;

import java.util.List;

public class InvalidBoardGameException extends RuntimeException {

    private List<String> boardGameExceptions;

    public InvalidBoardGameException(List<String> boardGameExceptions) {
        this.boardGameExceptions = boardGameExceptions;
    }

    public List<String> getBoardGameExceptions() {
        return boardGameExceptions;
    }
}
