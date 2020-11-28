package com.boardgamesworld.bgrental.boardgame.infrastructure.api;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;

public class BoardGameMapper {


    public static BoardGameDto toDto(BoardGame boardGame) {
        return new BoardGameDto(
                boardGame.getBoardGameId(),
                boardGame.getName(),
                boardGame.getPricePerDay(),
                boardGame.isRented(),
                boardGame.getCondition(),
                boardGame.getDetails()
        );
    }

    public static BoardGame fromDto(BoardGameDto boardGameDto) {
        return new BoardGame(
                boardGameDto.getBoardGameId(),
                boardGameDto.getName(),
                boardGameDto.getPricePerDay(),
                boardGameDto.isRented(),
                boardGameDto.getCondition(),
                boardGameDto.getDetails()
        );
    }
}
