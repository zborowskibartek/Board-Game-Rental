package com.boardgamesworld.bgrental.boardgame.infrastructure.database;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameDetails;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
        Optional<BoardGame> boardGame = boardGameRepositorySQL.findById(boardGameId);
        return boardGame.get();
    }

    @Transactional
    @Override
    public void updateBoardGame(long boardGameId, BoardGame updatedBoardGame) {
        BoardGame boardGame = boardGameRepositorySQL.findById(boardGameId).get();
        updateBoardGameFields(boardGame, updatedBoardGame);
    }

    private void updateBoardGameFields(BoardGame boardGame, BoardGame updatedBoardGame) {
        boardGame.setName(updatedBoardGame.getName());
        boardGame.setPricePerDay(updatedBoardGame.getPricePerDay());
        boardGame.setRented(updatedBoardGame.isRented());
        boardGame.setCondition(updatedBoardGame.getCondition());
        updateBoardGameDetailsFields(boardGame.getDetails(), updatedBoardGame.getDetails());
    }

    private void updateBoardGameDetailsFields(BoardGameDetails details, BoardGameDetails updatedDetails) {
        details.setDescription(updatedDetails.getDescription());
        details.setMinPlayers(updatedDetails.getMinPlayers());
        details.setMaxPlayers(updatedDetails.getMaxPlayers());
        details.setAuthor(updatedDetails.getAuthor());
        details.setPublisher(updatedDetails.getPublisher());
        details.setTypes(updatedDetails.getTypes());
        details.setCategories(updatedDetails.getCategories());
    }

    @Override
    public void deleteBoardGame(long boardGameId) {
        try {
            boardGameRepositorySQL.deleteById(boardGameId);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }

}
