package com.boardgamesworld.bgrental.dao;

import com.boardgamesworld.bgrental.entities.BoardGame;

import java.util.List;

public interface BoardGameRepository {

    List<BoardGame> getAllBoardGames();

    BoardGame getBoardGame(int boardGameId);

    void addBoardGame(BoardGame boardGame);

    void updateBoardGame(int boardGameId, BoardGame updatedBoardGame);

    void deleteBoardGame(int boardGameId);


}
