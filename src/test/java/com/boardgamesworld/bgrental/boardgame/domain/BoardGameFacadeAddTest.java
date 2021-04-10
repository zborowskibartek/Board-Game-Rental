package com.boardgamesworld.bgrental.boardgame.domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


@Tag("BoardGameFacadeTests")
@Tag("BoardGameFacadeTestsWithInMemoryRepo")
class BoardGameFacadeAddTest {

    private BoardGameFacade boardGameFacade;
    private BoardGame boardGame;

    @BeforeEach
    void init() {
        boardGameFacade = new BoardGameFacadeConfiguration().boardGameFacade(new BoardGameRepositoryInMemory());
        boardGame = BoardGameBuilder.anyBoardGame();
    }


    @Test
    @DisplayName("should add board game")
    void shouldAddBoardGame() {
        //given
        boardGame = BoardGameBuilder.create().setBoardGameId(1).build();
        //when
        boardGameFacade.addBoardGame(boardGame);
        //then
        BoardGame resultBoardGame = boardGameFacade.getBoardGame(1);
        assertEquals(boardGame, resultBoardGame);
    }

    @Nested
    @DisplayName("should throw exception when")
    class BoardGameFacadeAddExceptionTest {

        @Test
        @DisplayName("name length is equal zero")
        void shouldThrowExceptionWhenNameLengthIsEqualZero() {
            //given
            boardGame = BoardGameBuilder.create().setName("").build();
            //then
            InvalidBoardGameException invalidBoardGameException = assertThrows(InvalidBoardGameException.class, () -> boardGameFacade.addBoardGame(boardGame));
            assertTrue(invalidBoardGameException.getBoardGameErrors().contains("Invalid board game name!"));
        }


        @Test
        @DisplayName("price per day is equal zero")
        void shouldThrowExceptionWhenPricePerDayIsEqualZero() {
            //given
            boardGame = BoardGameBuilder.create().setPricePerDay(0.0).build();
            //then
            InvalidBoardGameException invalidBoardGameException = assertThrows(InvalidBoardGameException.class, () -> boardGameFacade.addBoardGame(boardGame));
            assertTrue(invalidBoardGameException.getBoardGameErrors().contains("Price per day must be greater than 0!"));
        }

        @Test
        @DisplayName("price per day is less than zero")
        void shouldThrowExceptionWhenPricePerDayIsLessThanZero() {
            //given
            boardGame = BoardGameBuilder.create().setPricePerDay(-1.0).build();
            //then
            InvalidBoardGameException invalidBoardGameException = assertThrows(InvalidBoardGameException.class, () -> boardGameFacade.addBoardGame(boardGame));
            assertTrue(invalidBoardGameException.getBoardGameErrors().contains("Price per day must be greater than 0!"));
        }

        @Test
        @DisplayName("condition is null")
        void shouldThrowExceptionWhenConditionIsNull() {
            //given
            boardGame = BoardGameBuilder.create().setCondition(null).build();
            //then
            InvalidBoardGameException invalidBoardGameException = assertThrows(InvalidBoardGameException.class, () -> boardGameFacade.addBoardGame(boardGame));
            assertTrue(invalidBoardGameException.getBoardGameErrors().contains("Set board game condition!"));
        }
    }
}


