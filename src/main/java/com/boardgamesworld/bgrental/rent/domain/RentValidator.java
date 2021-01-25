package com.boardgamesworld.bgrental.rent.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameFacade;
import com.boardgamesworld.bgrental.user.domain.User;
import com.boardgamesworld.bgrental.user.domain.UserFacade;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
class RentValidator {

    private final BoardGameFacade boardGameFacade;
    private final UserFacade userFacade;

    void validateRent(long boardGameId, long userId) {
        List<String> errors = new ArrayList<>();
        BoardGame boardGame = boardGameFacade.getBoardGame(boardGameId);
        User user = userFacade.getUser(userId);

        if (boardGame == null) {
            errors.add("Board game do not exist! Choose another one!");
        }
        if (boardGame != null && boardGame.isRented()) {
            errors.add("Board game is rented! Choose another one!");
        }
        if (user == null) {
            errors.add("User do not exist! Choose another one!");
        }
        if (errors.size() > 0) {
            throw new InvalidRentException(errors);
        }
    }

    void validateReturn(long boardGameId) {
        List<String> errors = new ArrayList<>();
        BoardGame boardGame = boardGameFacade.getBoardGame(boardGameId);

        if (boardGame == null) {
            errors.add("Board game do not exist! Choose another one!");
        }
        if (boardGame != null && !boardGame.isRented()) {
            errors.add("Board game is already returned! Choose another one!");
        }
        if (errors.size() > 0) {
            throw new InvalidRentException(errors);
        }
    }
}
