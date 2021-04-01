package com.boardgamesworld.bgrental.rent.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameFacade;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameFacadeConfiguration;
import com.boardgamesworld.bgrental.rent.infrastructure.database.inmemory.RentRepositoryInMemory;
import com.boardgamesworld.bgrental.renthistory.domain.RentHistoryFacade;
import com.boardgamesworld.bgrental.renthistory.domain.RentHistoryFacadeConfiguration;
import com.boardgamesworld.bgrental.user.domain.User;
import com.boardgamesworld.bgrental.user.domain.UserFacade;
import com.boardgamesworld.bgrental.user.domain.UserFacadeConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RentFacadeTest {

    private RentFacade rentFacade;
    private BoardGameFacade boardGameFacade;
    private RentHistoryFacade rentHistoryFacade;
    private UserFacade userFacade;
    private RentRepository rentRepository;


    private static final int GAME_ID = 1;
    private static final int USER_ID = 3;
    private static final LocalDateTime DATE = LocalDateTime.of(2, Month.APRIL, 3, 4, 5);

    @BeforeEach
    void setup() {
        boardGameFacade = mock(BoardGameFacade.class);
        rentHistoryFacade = mock(RentHistoryFacade.class);
        userFacade = mock(UserFacade.class);
        rentRepository = mock(RentRepository.class);
        rentFacade = new RentFacadeConfiguration().rentFacade(rentRepository, boardGameFacade, userFacade, rentHistoryFacade);
    }

    @Disabled
    @Test
    void rentBoardGameShouldAddBoardGameToRentRepositoryChangeStatusForTrueAndValidateData() {
        //given
        Rent rent = new Rent(GAME_ID, USER_ID, DATE);
        BoardGame boardGame = new BoardGame(GAME_ID, null, 0.00, false, null, null);
        User user = new User(USER_ID, null, null, null, null, null);

        doNothing().when(rentRepository).addRent(rent);
        when(rentRepository.getAllRents()).thenReturn(new ArrayList<>(Collections.singletonList(rent)));
        when(boardGameFacade.getBoardGame(GAME_ID)).thenReturn(boardGame);
        when(userFacade.getUser(USER_ID)).thenReturn(user);

        //when
        rentFacade.rentBoardGame(GAME_ID, USER_ID);
        //then
        assertEquals(rentRepository.getAllRents().size(), 1);
        verify(userFacade, times(1)).getUser(USER_ID);
        verify(boardGameFacade, times(2)).getBoardGame(GAME_ID);
    }

    @Test
    void getAllRents() {
        //given
        Rent rent1 = new Rent(GAME_ID, USER_ID, DATE);
        Rent rent2 = new Rent(GAME_ID, USER_ID, DATE);
        List<Rent> rents = new ArrayList<>(Arrays.asList(rent1, rent2));
        when(rentRepository.getAllRents()).thenReturn(rents);
        //when
        List<Rent> allRents = rentFacade.getAllRents();
        //then
        assertEquals(allRents, rents);
        verify(rentRepository).getAllRents();
    }
    @Disabled

    @Test
    void getAllRentsByUser() {
        //given
        Rent rent1 = new Rent(2, USER_ID, DATE);
        Rent rent2 = new Rent(1, USER_ID, DATE);
        List<Rent> rents = new ArrayList<>(Arrays.asList(rent1, rent2));
        when(rentRepository.getAllRentsByUser(USER_ID)).thenReturn(rents);
        //when
        List<Rent> allRents = rentFacade.getAllRentsByUser(USER_ID);
        //then
        assertTrue(allRents.containsAll(Arrays.asList(rent1, rent2)));
        verify(rentRepository).getAllRentsByUser(USER_ID);
    }

    @Disabled
    @Test
    void returnBoardGame() {
    }

    @Disabled
    @Test
    void getAllRentBoardGames() {
    }

    @Disabled
    @Test
    void getAllRentBoardGamesByUser() {
    }
}