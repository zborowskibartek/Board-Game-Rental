package com.boardgamesworld.bgrental.boardgame.domain;

import java.util.ArrayList;
import java.util.List;

class BoardGameValidator {

    void validateBoardGame(BoardGame boardGame) {
        List<String> errors = new ArrayList<>();
        if (!isNameValid(boardGame.getName())) {
            errors.add("Invalid board game name!");
        }
        if (!isPriceValid(boardGame.getPricePerDay())) {
            errors.add("Price per day must be greater than 0!");
        }
        if (!isConditionValid(boardGame.getCondition())) {
            errors.add("Set board game condition!");
        }
        if (errors.size() > 0) {
            throw new InvalidBoardGameException(errors);
        }
    }

    private boolean isNameValid(String name) {
        return name != null && !name.equals("");
    }

    private boolean isPriceValid(double price) {
        return price > 0.0;
    }

    private boolean isConditionValid(BoardGameCondition condition) {
        return condition != null;
    }


}
