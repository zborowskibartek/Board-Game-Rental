package com.boardgamesworld.bgrental.repositories;

import com.boardgamesworld.bgrental.model.*;
import com.boardgamesworld.bgrental.model.details.BoardGameCategory;
import com.boardgamesworld.bgrental.model.details.BoardGameCondition;
import com.boardgamesworld.bgrental.model.details.BoardGameDetails;
import com.boardgamesworld.bgrental.model.details.BoardGameType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Primary
@Repository
public class BoardGameRepositoryInMemory implements BoardGameRepository {

    private Map<Long, BoardGame> boardGames;


    public BoardGameRepositoryInMemory() {
        boardGames = new HashMap<>();
        createInitialBoardGames(boardGames);
    }

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
    public void updateBoardGame(long boardGameIdToUpdate, BoardGame boardGameWithUpdatedProperties) {
        if (isBoardGameInRepository(boardGameIdToUpdate)) {
            updateBoardGameProperties(getBoardGame(boardGameIdToUpdate), boardGameWithUpdatedProperties);
        }
    }

    @Override
    public void deleteBoardGame(long boardGameId) {
        if (isBoardGameInRepository(boardGameId)) {
            boardGames.remove(boardGameId);
        }
    }

    private boolean isBoardGameInRepository(long boardGameId) {
        if (boardGames.containsKey(boardGameId)) {
            return true;
        }
        throw new IllegalArgumentException("Wrong board game ID. Can not find in repository!");
    }

    private void updateBoardGameProperties(BoardGame boardGameToUpdate, BoardGame boardGameWithUpdatedProperties) {
        if (boardGameWithUpdatedProperties.getName() != null) {
            boardGameToUpdate.setName(boardGameWithUpdatedProperties.getName());
        }
        if (boardGameWithUpdatedProperties.getPricePerDay() < 0) {
            boardGameToUpdate.setPricePerDay(boardGameWithUpdatedProperties.getPricePerDay());
        }
        if (boardGameWithUpdatedProperties.getCondition() != null) {
            boardGameToUpdate.setCondition(boardGameWithUpdatedProperties.getCondition());
        }
        if (boardGameWithUpdatedProperties.getDetails() != null) {
            boardGameToUpdate.setDetails(boardGameWithUpdatedProperties.getDetails());
        }
        boardGameToUpdate.setRented(boardGameWithUpdatedProperties.isRented());
    }

    private static void createInitialBoardGames(Map boardGames) {
        BoardGameDetails boardGameDetails =
                new BoardGameDetails("Heavy game",
                        1,
                        5,
                        "Isac",
                        "Albi",
                        Arrays.asList(BoardGameType.BOARD_GAME, BoardGameType.CARD_GAME),
                        Arrays.asList(BoardGameCategory.ADVENTURE, BoardGameCategory.FANTASY, BoardGameCategory.STRATEGY)
                );

        BoardGame boardGame1 = new BoardGame(1, "Imperial Settlers", 10, false,
                BoardGameCondition.GOOD, new BoardGameDetails("Cool game"));
        BoardGame boardGame2 = new BoardGame(2, "Gloomhaven", 20, true,
                BoardGameCondition.PERFECT, boardGameDetails);
        boardGames.put(boardGame1.getBoardGameId(), boardGame1);
        boardGames.put(boardGame2.getBoardGameId(), boardGame2);
    }

}
