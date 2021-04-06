package com.boardgamesworld.bgrental.boardgame.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Tag("BoardGameFacadeTests")
@Tag("BoardGameFacadeTestsWithMockedIO")
class BoardGameFacadeTest {

    private BoardGameFacade boardGameFacade;
    private BoardGameRepository boardGameRepository;

    @BeforeEach
    void setup() {
        boardGameRepository = mock(BoardGameRepository.class);
        boardGameFacade = new BoardGameFacadeConfiguration().boardGameFacade(boardGameRepository);
    }

    @Test
    @DisplayName("should add board game")
    void shouldAddBoardGame() {
        //given
        BoardGame boardGame = BoardGameBuilder.anyBoardGame();
        //when
        boardGameFacade.addBoardGame(boardGame);
        //then
        verify(boardGameRepository, times(1)).addBoardGame(boardGame);
    }

    @Test
    @DisplayName("should get board game")
    void shouldGetBoardGame() {
        //given
        BoardGame boardGame = BoardGameBuilder.create().setBoardGameId(1).build();
        when(boardGameRepository.getBoardGame(1)).thenReturn(boardGame);
        //when
        BoardGame receivedBoardGame = boardGameFacade.getBoardGame(1);
        //then
        verify(boardGameRepository, times(1)).getBoardGame(1);
        assertEquals(boardGame, receivedBoardGame);
    }

    @Test
    @DisplayName("should update board game")
    void shouldUpdateBoardGame() {
        //given
        BoardGame boardGame = BoardGameBuilder.create().setBoardGameId(1).build();
        //when
        boardGameFacade.updateBoardGame(1, boardGame);
        //then
        verify(boardGameRepository, times(1)).updateBoardGame(1, boardGame);
    }

}