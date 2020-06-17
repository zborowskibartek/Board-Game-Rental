package com.boardgamesworld.bgrental.repositories.hashmap;

import com.boardgamesworld.bgrental.model.BoardGame;
import com.boardgamesworld.bgrental.model.BoardGameCondition;
import com.boardgamesworld.bgrental.model.BoardGameDetails;
import com.boardgamesworld.bgrental.repositories.interfaces.BoardGameRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Primary
@Repository
public class BoardGameHashMapRepository implements BoardGameRepository {

    private Map<Long, BoardGame> boardGames;


    public BoardGameHashMapRepository() {
        boardGames = new HashMap<>();

        BoardGame boardGame1 = new BoardGame(1,"Imperial Settlers", 10, false,
                BoardGameCondition.GOOD, new BoardGameDetails("Cool game"));
        BoardGame boardGame2 = new BoardGame(2, "Gloomhaven", 20, true,
                BoardGameCondition.PERFECT, new BoardGameDetails("Heavy game"));
        boardGames.put(boardGame1.getBoardGameId(), boardGame1);
        boardGames.put(boardGame2.getBoardGameId(), boardGame2);
    }

    @Override
    public List<BoardGame> getAllBoardGames() {
        return boardGames.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
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
        if (isBoardGameInRepository(boardGameId)) {
            BoardGame boardGameToUpdate = boardGames.get(boardGameId);

            if (updatedBoardGame.getName() != null){
                boardGameToUpdate.setName(updatedBoardGame.getName());
            }
            if (updatedBoardGame.getPricePerDay() < 0){
                boardGameToUpdate.setPricePerDay(updatedBoardGame.getPricePerDay());
            }
                boardGameToUpdate.setRented(updatedBoardGame.getRented());
            if (updatedBoardGame.getCondition() != null){
                boardGameToUpdate.setCondition(updatedBoardGame.getCondition());
            }
            if (updatedBoardGame.getDetails() != null){
                boardGameToUpdate.setDetails(updatedBoardGame.getDetails());
            }
        }
    }

    @Override
    public void deleteBoardGame(long boardGameId) {
        if (isBoardGameInRepository(boardGameId)) {
            boardGames.remove(boardGameId);
        }
    }

    private boolean isBoardGameInRepository(long boardGameId){
        if (boardGames.containsKey(boardGameId)) {
            return true;
        }
            throw new IllegalArgumentException("Wrong board game ID. Can not find in repository!");
    }

    @Override
    public String toString() {
        return "BoardGameHashMapRepository{" +
                "boardGameMap=" + boardGames +
                '}';
    }
}
