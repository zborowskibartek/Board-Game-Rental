package com.boardgamesworld.bgrental.boardgame.infrastructure.api;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameDetails;

public class BoardGameMapper {


    public static BoardGameDto toDto(BoardGame boardGame) {
        return new BoardGameDto(
                boardGame.getBoardGameId(),
                boardGame.getName(),
                boardGame.getPricePerDay(),
                boardGame.isRented(),
                boardGame.getCondition(),
                getBoardGameDetailsDto(boardGame.getDetails())
        );
    }

    public static BoardGame fromDto(BoardGameDto boardGameDto) {
        return new BoardGame(
                boardGameDto.getBoardGameId(),
                boardGameDto.getName(),
                boardGameDto.getPricePerDay(),
                boardGameDto.isRented(),
                boardGameDto.getCondition(),
                getBoardGameDetails(boardGameDto.getDetails()));
    }

    private static BoardGameDetailsDto getBoardGameDetailsDto(BoardGameDetails boardGameDetails) {
        return new BoardGameDetailsDto(
                boardGameDetails.getDescription(),
                boardGameDetails.getMinPlayers(),
                boardGameDetails.getMaxPlayers(),
                boardGameDetails.getAuthor(),
                boardGameDetails.getPublisher(),
                boardGameDetails.getTypes(),
                boardGameDetails.getCategories()
        );
    }

    private static BoardGameDetails getBoardGameDetails(BoardGameDetailsDto boardGameDetailsDto) {
        return new BoardGameDetails(boardGameDetailsDto.getDescription(),
                boardGameDetailsDto.getMinPlayers(),
                boardGameDetailsDto.getMaxPlayers(),
                boardGameDetailsDto.getAuthor(),
                boardGameDetailsDto.getPublisher(),
                boardGameDetailsDto.getTypes(),
                boardGameDetailsDto.getCategories()
        );
    }
}