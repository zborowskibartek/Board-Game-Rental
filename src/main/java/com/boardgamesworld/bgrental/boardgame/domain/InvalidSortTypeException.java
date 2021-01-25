package com.boardgamesworld.bgrental.boardgame.domain;

import java.util.Arrays;

public class InvalidSortTypeException extends RuntimeException {

    public InvalidSortTypeException(String message) {
        super("Invalid sort parameter " + message + ". Available values: " + Arrays.toString(BoardGameSortType.values()));
    }
}
