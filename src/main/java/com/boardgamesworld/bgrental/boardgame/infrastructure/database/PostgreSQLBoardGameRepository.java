package com.boardgamesworld.bgrental.boardgame.infrastructure.database;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
@Primary
public class PostgreSQLBoardGameRepository implements BoardGameRepository {

    private final BoardGameRepositorySQL boardGameRepositorySQL;

    @Override
    public void addBoardGame(BoardGame boardGame) {
        boardGameRepositorySQL.save(boardGame);
    }

    @Override
    public List<BoardGame> getAllBoardGames() {
        return boardGameRepositorySQL.findAll();
    }

    @Override
    public BoardGame getBoardGame(long boardGameId) {
        Optional<BoardGame> byId = boardGameRepositorySQL.findById(boardGameId);
        return byId.get();
    }

    @Override
    public void updateBoardGame(long boardGameId, BoardGame updatedBoardGame) {
        boardGameRepositorySQL.deleteById(boardGameId);
        boardGameRepositorySQL.save(updatedBoardGame);
    }

    @Override
    public void deleteBoardGame(long boardGameId) {
        try {
            boardGameRepositorySQL.deleteById(boardGameId);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }

}
