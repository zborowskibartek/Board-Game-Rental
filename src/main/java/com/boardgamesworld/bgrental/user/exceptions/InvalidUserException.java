package com.boardgamesworld.bgrental.user.exceptions;

import java.util.List;

public class InvalidUserException extends RuntimeException {

    private final List<String> userExceptions;

    public InvalidUserException(List<String> userExceptions){
        this.userExceptions = userExceptions;
    }

    public List<String> getUserExceptions() {
        return userExceptions;
    }
}