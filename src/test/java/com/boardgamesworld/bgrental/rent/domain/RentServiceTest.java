package com.boardgamesworld.bgrental.rent.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RentServiceTest {

    private static final int GAME_ID = 1;
    private static final int USER_ID = 3;
    private static final LocalDateTime DATE = LocalDateTime.of(2, Month.APRIL, 3, 4, 5);
    private RentService rentService;
    private RentRepository rentRepository;

    @BeforeEach
    void setup() {
        rentRepository = mock(RentRepository.class);
        rentService = new RentService(rentRepository, null, null, null);
    }

    @Test
    void getAllRents() {
        //given
        Rent rent1 = new Rent(GAME_ID, USER_ID, DATE);
        Rent rent2 = new Rent(GAME_ID, USER_ID, DATE);
        List<Rent> rents = new ArrayList<>(Arrays.asList(rent1, rent2));
        when(rentRepository.getAllRents()).thenReturn(rents);

        //when
        List<Rent> allRents = rentService.getAllRents();
        //then
        assertEquals(allRents, rents);

    }
}