package com.boardgamesworld.bgrental.boardgame.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("BoardGameFacadeTests")
@Tag("BoardGameFacadeTestsWithInMemoryRepo")
class BoardGameFacadeUpdateAndDeleteTest {
    private BoardGameFacade boardGameFacade;
    private BoardGameBuilder boardGameBuilder;

    @BeforeEach
    void init() {
        boardGameFacade = new BoardGameFacadeConfiguration().boardGameFacade(new BoardGameRepositoryInMemory());
        boardGameBuilder = BoardGameBuilder.create();
    }

    @Test
    @DisplayName("should update board game")
    void shouldUpdateBoardGame() {
        //given
        boardGameFacade.addBoardGame(boardGameBuilder
                .setBoardGameId(1)
                .setName("Test Name")
                .setPricePerDay(20.0)
                .isRented(false)
                .setCondition(BoardGameCondition.PERFECT)
                .setDetails(BoardGameBuilder.BoardGameDetailsBuilder.create()
                        .setDescription("Test Description")
                        .setMinPlayers(1)
                        .setMaxPlayers(2)
                        .setAuthor("Test Author")
                        .setPublisher("Test Publisher")
                        .setTypes(new HashSet<>(Arrays.asList(BoardGameType.DICE_GAME, BoardGameType.CARD_GAME)))
                        .setCategories(new HashSet<>(Arrays.asList(BoardGameCategory.FANTASY, BoardGameCategory.COOPERATIVE))).build()).build());
        BoardGame updates = boardGameBuilder
                .setBoardGameId(1)
                .setName("Test Name UPDATED")
                .setPricePerDay(20.0)
                .isRented(false)
                .setCondition(BoardGameCondition.PERFECT)
                .setDetails(BoardGameBuilder.BoardGameDetailsBuilder.create()
                        .setDescription("Test Description UPDATED")
                        .setMinPlayers(1)
                        .setMaxPlayers(2)
                        .setAuthor("Test Author")
                        .setPublisher(null)
                        .setTypes(new HashSet<>(Arrays.asList(BoardGameType.DICE_GAME, BoardGameType.CARD_GAME)))
                        .setCategories(new HashSet<>(Arrays.asList(BoardGameCategory.FANTASY, BoardGameCategory.COOPERATIVE))).build()).build();
        //when
        boardGameFacade.updateBoardGame(1, updates);
        BoardGame updatedBoardGame = boardGameFacade.getBoardGame(1);
        //then
        assertAll(
                () -> assertEquals("Test Name UPDATED", updatedBoardGame.getName()),
                () -> assertEquals("Test Description UPDATED", updatedBoardGame.getDetails().getDescription()),
                () -> assertNull(updatedBoardGame.getDetails().getPublisher())
        );
    }

    @Test
    @DisplayName("should remove board game")
    void shouldDeleteBoardGame() {
        //given
        boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(1).build());
        //when
        boardGameFacade.deleteBoardGame(1);
        List<BoardGame> boardGames = boardGameFacade.getAllBoardGames(null, null, null, 0, 2);
        //then
        assertEquals(0, boardGames.size());
    }
}
