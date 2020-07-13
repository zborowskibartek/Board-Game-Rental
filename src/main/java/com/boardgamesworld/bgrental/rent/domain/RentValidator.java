package com.boardgamesworld.bgrental.rent.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGameFacade;
import com.boardgamesworld.bgrental.user.domain.UserFacade;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
class RentValidator {

    private final BoardGameFacade boardGameFacade;
    private final UserFacade userFacade;

    boolean validateRent(long boardGameId, long userId) {
        List<String> errors = new ArrayList<>();
        if (boardGameFacade.getBoardGame(boardGameId) == null) {
            errors.add("Board game do not exist! Choose another one!");
        }
        if (boardGameFacade.getBoardGame(boardGameId).isRented()) {
            errors.add("Board game is rented! Choose another one!");
        }
        if (userFacade.getUser(userId) == null) {
            errors.add("User do not exist! Choose another one!");
        }
        if (errors.size() > 0) {
            throw new InvalidRentException(errors);
        }
        return true;
    }
}
