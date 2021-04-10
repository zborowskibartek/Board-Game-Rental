package com.boardgamesworld.bgrental.rent.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameBuilder;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameFacade;
import com.boardgamesworld.bgrental.renthistory.domain.RentHistoryFacade;
import com.boardgamesworld.bgrental.user.domain.User;
import com.boardgamesworld.bgrental.user.domain.UserBuilder;
import com.boardgamesworld.bgrental.user.domain.UserFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@Tag("RentFacadeTests")
@Tag("RentFacadeTestsWithMockedIO")
class RentFacadeTest {

    private RentFacade rentFacade;
    private BoardGameFacade boardGameFacade;
    private RentHistoryFacade rentHistoryFacade;
    private UserFacade userFacade;
    private RentRepository rentRepository;

    @BeforeEach
    void setup() {
        boardGameFacade = mock(BoardGameFacade.class);
        rentHistoryFacade = mock(RentHistoryFacade.class);
        userFacade = mock(UserFacade.class);
        rentRepository = mock(RentRepository.class);
        rentFacade = new RentFacadeConfiguration().rentFacade(rentRepository, boardGameFacade, userFacade, rentHistoryFacade);
    }

    @Test
    void shouldRentBoardGame() {
        //given
        User user = UserBuilder.anyUser();
        BoardGame boardGame = BoardGameBuilder.anyBoardGame();
        when(boardGameFacade.getBoardGame(boardGame.getBoardGameId())).thenReturn(boardGame);
        when(userFacade.getUser(user.getUserId())).thenReturn(user);
        //when
        rentFacade.rentBoardGame(boardGame.getBoardGameId(), user.getUserId());
        //then
        verify(rentRepository, times(1)).addRent(any(Rent.class));
    }

    @Test
    void getAllRents() {
    }

    @Test
    void getAllRentsByUser() {
    }

    @Test
    void returnBoardGame() {
    }

    @Test
    void getAllRentBoardGames() {
    }

    @Test
    void getAllRentBoardGamesByUser() {
    }
}