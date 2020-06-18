package com.boardgamesworld.bgrental.repositories.mongodb;

import com.boardgamesworld.bgrental.model.BoardGame;
import com.boardgamesworld.bgrental.repositories.interfaces.BoardGameRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardGameMongoDbRepository implements BoardGameRepository {

    @Override
    public List<BoardGame> getAllBoardGames() {
        return null;
    }

    @Override
    public BoardGame getBoardGame(long boardGameId) {
        return null;
    }

    @Override
    public void addBoardGame(BoardGame boardGame) {

    }

    @Override
    public void updateBoardGame(long boardGameId, BoardGame updatedBoardGame) {

    }

    @Override
    public void deleteBoardGame(long boardGameId) {

    }
}
