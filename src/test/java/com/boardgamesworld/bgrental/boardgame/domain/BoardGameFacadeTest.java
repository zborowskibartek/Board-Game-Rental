package com.boardgamesworld.bgrental.boardgame.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameFacadeTest {

    private BoardGameFacade boardGameFacade;
    private BoardGame sample;

    @BeforeAll
    static void setup() {
    }

    @BeforeEach
    void init() {
        boardGameFacade = new BoardGameFacadeConfiguration().boardGameFacade(new BoardGameRepositoryInMemory());
        sample = new BoardGame(1, "Too Many Bones", 14.0, false, BoardGameCondition.PERFECT,
                new BoardGameDetails("Too looong game!", 1, 5, "Who", "ChipTheoryGames",
                        new ArrayList<>(Arrays.asList(BoardGameType.DICE_GAME, BoardGameType.CARD_GAME)),
                        new ArrayList<>(Arrays.asList(BoardGameCategory.FANTASY, BoardGameCategory.COOPERATIVE))
                ));
    }

    @Test
    void shouldReturnBoardGameById() {
        //given
        //when
        boardGameFacade.addBoardGame(sample);
        BoardGame result = boardGameFacade.getBoardGame(1);
        //then
        assertSame(sample, result); //?
        assertEquals(sample, result); //?
    }


    @Test
    void shouldAddBoardGame() {
        //given
        //when
        boardGameFacade.addBoardGame(sample);
        BoardGame result = boardGameFacade.getBoardGame(1);
        //then
        assertSame(sample, result);
    }

    @Test
    void shouldThrowExceptionWhenAddBoardGameWithNullName() throws Throwable {
        BoardGame boardGame = new BoardGame(1, null, 14.0, false, BoardGameCondition.PERFECT,
                new BoardGameDetails("Too looong game!", 1, 5, "Who", "ChipTheoryGames",
                        new ArrayList<>(Arrays.asList(BoardGameType.DICE_GAME, BoardGameType.CARD_GAME)),
                        new ArrayList<>(Arrays.asList(BoardGameCategory.FANTASY, BoardGameCategory.COOPERATIVE))
                ));
        assertThrows(InvalidBoardGameException.class, () -> boardGameFacade.addBoardGame(boardGame));
    }

    @Test
    void shouldDeleteBoardGame() {
        //given
        boardGameFacade.addBoardGame(sample);
        //when
        boardGameFacade.deleteBoardGame(1);
        //then
        BoardGame result = boardGameFacade.getBoardGame(1);
        assertNull(result);
    }
}