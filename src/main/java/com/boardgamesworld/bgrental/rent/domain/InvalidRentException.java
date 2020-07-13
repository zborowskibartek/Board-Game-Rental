package com.boardgamesworld.bgrental.rent.domain;

import java.util.List;

public class InvalidRentException extends RuntimeException {

    private final List<String> rentExceptions;

    public InvalidRentException(List<String> rentExceptions) {
        this.rentExceptions = rentExceptions;
    }

    public List<String> getRentExceptions() {
        return rentExceptions;
    }
}