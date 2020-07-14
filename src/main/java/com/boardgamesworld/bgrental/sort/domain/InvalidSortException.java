package com.boardgamesworld.bgrental.sort.domain;

public class InvalidSortException extends RuntimeException {

    private final String message;

    public InvalidSortException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
