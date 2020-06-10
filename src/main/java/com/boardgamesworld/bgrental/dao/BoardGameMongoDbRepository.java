package com.boardgamesworld.bgrental.dao;

import com.boardgamesworld.bgrental.entities.BoardGame;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardGameMongoDbRepository implements BoardGameRepository {

    @Override
    public List<BoardGame> getAllBoardGames() {
        return null;
    }

    @Override
    public BoardGame getBoardGame(int boardGameId) {
        return null;
    }

    @Override
    public void addBoardGame(BoardGame boardGame) {

    }

    @Override
    public void updateBoardGame(int boardGameId, BoardGame updatedBoardGame) {

    }

    @Override
    public void deleteBoardGame(int boardGameId) {

    }
}
