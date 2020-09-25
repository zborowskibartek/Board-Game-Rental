package com.boardgamesworld.bgrental.boardgame.domain;

import java.util.List;

public interface BoardGameRepository {

    List<BoardGame> getAllBoardGames();

    BoardGame getBoardGame(long boardGameId);

    void addBoardGame(BoardGame boardGame);

    void updateBoardGame(long boardGameId, BoardGame updatedBoardGame);

    void deleteBoardGame(long boardGameId);

}
