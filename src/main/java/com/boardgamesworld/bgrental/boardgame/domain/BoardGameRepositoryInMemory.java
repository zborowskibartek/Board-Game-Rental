package com.boardgamesworld.bgrental.boardgame.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
class BoardGameRepositoryInMemory implements BoardGameRepository {

    private final Map<Long, BoardGame> boardGames = new HashMap<>();

    @Override
    public List<BoardGame> getAllBoardGames() {
        return new ArrayList<>(boardGames.values());
    }

    @Override
    public BoardGame getBoardGame(long boardGameId) {
        return boardGames.get(boardGameId);
    }

    @Override
    public void addBoardGame(BoardGame boardGame) {
        boardGames.put(boardGame.getBoardGameId(), boardGame);
    }

    @Override
    public void updateBoardGame(long boardGameId, BoardGame updatedBoardGame) {
        boardGames.put(boardGameId, updatedBoardGame);
    }

    @Override
    public void deleteBoardGame(long boardGameId) {
        boardGames.remove(boardGameId);
    }
}
