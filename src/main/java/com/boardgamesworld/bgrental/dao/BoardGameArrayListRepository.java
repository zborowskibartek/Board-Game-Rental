package com.boardgamesworld.bgrental.dao;

import com.boardgamesworld.bgrental.entities.BoardGame;
import com.boardgamesworld.bgrental.entities.BoardGameCondition;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
public class BoardGameArrayListRepository implements BoardGameRepository {

    private List<BoardGame> boardGameList;

    public BoardGameArrayListRepository() {
        boardGameList = new ArrayList<>();
        boardGameList.add(new BoardGame(1L, "Imperial Settlers", 10,
                BoardGameCondition.GOOD, "Cool game", false));
        boardGameList.add(new BoardGame(2L, "Gloomhaven", 20,
                BoardGameCondition.PERFECT, "Heavy game", true));
    }

    @Override
    public List<BoardGame> getAllBoardGames() {
        return boardGameList;
    }

    @Override
    public BoardGame getBoardGame(int boardGameId) {
        return boardGameList.stream()
                .filter(boardGame -> boardGame.getBoardGameId() == boardGameId)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Wrong board game ID. Can not find on list!"));
    }

    @Override
    public void addBoardGame(BoardGame boardGame) {
        boardGameList.add(boardGame);
    }

    @Override
    public void updateBoardGame(int boardGameId, BoardGame updatedBoardGame) {
        boardGameList.stream()
                .filter(boardGame -> boardGame.getBoardGameId() == boardGameId)
                .findAny()
                .ifPresent(boardGame -> {
                    boardGame.setName(updatedBoardGame.getName());
                    boardGame.setPricePerDay(updatedBoardGame.getPricePerDay());
                    boardGame.setCondition(updatedBoardGame.getCondition());
                    boardGame.setDescription(updatedBoardGame.getDescription());
                    boardGame.setRented(updatedBoardGame.getRented());
                });
    }

    @Override
    public void deleteBoardGame(int boardGameId) {
        boardGameList.stream()
                .filter(boardGame -> boardGame.getBoardGameId() == boardGameId)
                .findAny()
                .ifPresent(boardGame -> boardGameList.remove(boardGame));
    }

}
