package com.boardgamesworld.bgrental.repositories;

import com.boardgamesworld.bgrental.model.BoardGame;
import com.boardgamesworld.bgrental.repositories.interfaces.BoardGameRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class BoardGameArrayListRepository implements BoardGameRepository {

    private List<BoardGame> boardGameList;


    public BoardGameArrayListRepository() {
        boardGameList = new ArrayList<>();

  /*      boardGameList.add(new BoardGame("Imperial Settlers", 10, false,
                BoardGameCondition.GOOD, new BoardGameDetails("Cool game")));
        boardGameList.add(new BoardGame("Gloomhaven", 20, true,
                BoardGameCondition.PERFECT, new BoardGameDetails("Heavy game")));*/
    }

    @Override
    public List<BoardGame> getAllBoardGames() {
        return boardGameList;
    }

    @Override
    public BoardGame getBoardGame(long boardGameId) {
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
    public void updateBoardGame(long boardGameId, BoardGame updatedBoardGame) {
        boardGameList.stream()
                .filter(boardGame -> boardGame.getBoardGameId() == boardGameId)
                .findAny()
                .ifPresent(boardGame -> {
                    if (updatedBoardGame.getName() != null) {
                        boardGame.setName(updatedBoardGame.getName());
                    }
                    if (updatedBoardGame.getPricePerDay() > 0) {
                        boardGame.setPricePerDay(updatedBoardGame.getPricePerDay());
                    }
                    if (updatedBoardGame.getCondition() != null) {
                        boardGame.setCondition(updatedBoardGame.getCondition());
                    }
                    if (updatedBoardGame.getDetails().getDescription() != null) {
                        boardGame.getDetails().setDescription(updatedBoardGame.getDetails().getDescription());
                    }
                    boardGame.setRented(updatedBoardGame.isRented());
                });
    }


    @Override
    public void deleteBoardGame(long boardGameId) {
        boardGameList.stream()
                .filter(boardGame -> boardGame.getBoardGameId() == boardGameId)
                .findAny()
                .ifPresent(boardGame -> boardGameList.remove(boardGame));
    }

}

