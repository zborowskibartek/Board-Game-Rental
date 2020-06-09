package com.boardgamesworld.bgrental.services;

import com.boardgamesworld.bgrental.entities.BoardGame;
import com.boardgamesworld.bgrental.entities.BoardGameCondition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardGameService {

    private List<BoardGame> boardGameList;

    public BoardGameService() {
        boardGameList = new ArrayList<>();
        boardGameList.add(new BoardGame(1L, "Imperial Settlers", 10,
                BoardGameCondition.GOOD, "Cool game", false));
        boardGameList.add(new BoardGame(2L, "Gloomhaven", 20,
                BoardGameCondition.PERFECT, "Heavy game", true));
    }

    public List<BoardGame> getAllBoardGames() {
        return boardGameList;
    }

    public BoardGame getBoardGame(int boardGameId) {
        return boardGameList.stream()
                .filter(boardGame -> boardGame.getBoardGameId() == boardGameId)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Wrong board game ID. Can not find on list!"));
    }

    public void addBoardGame(BoardGame boardGame) {
        boardGameList.add(boardGame);
    }

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

    public void deleteBoardGame(int boardGameId) {
        boardGameList.stream()
                .filter(boardGame -> boardGame.getBoardGameId() == boardGameId)
                .findAny()
                .ifPresent(boardGame -> boardGameList.remove(boardGame));
    }


}
