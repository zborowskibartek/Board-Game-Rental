package com.boardgamesworld.bgrental.boardgame.domain;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameFacadeGetTest {

    private BoardGameFacade boardGameFacade;
    private BoardGameBuilder boardGameBuilder;

    @BeforeEach
    void init() {
        boardGameFacade = new BoardGameFacadeConfiguration().boardGameFacade(new BoardGameRepositoryInMemory());
        boardGameBuilder = BoardGameBuilder.create();
    }

    @Test
    @DisplayName("should get board game")
    void shouldGetBoardGame() {
        //given
        BoardGame boardGame = boardGameBuilder.setBoardGameId(1).build();
        boardGameFacade.addBoardGame(boardGame);
        //when
        BoardGame resultBoardGame = boardGameFacade.getBoardGame(1);
        //then
        assertEquals(boardGame, resultBoardGame);
    }

    @Test
    @DisplayName("should return null when board game is not present")
    void shouldReturnNullWhenBordGameIsNotPresent() {
        //when
        BoardGame resultBoardGame = boardGameFacade.getBoardGame(1);
        //then
        assertNull(resultBoardGame);
    }

    @Nested
    @DisplayName("should return all board games")
    class BoardGameFacadeGetSortAndFilterTest {
        @Test
        @DisplayName("sorted by default")
        void shouldReturnAllBoardGamesSortedByDefault() {
            //given

            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(1).setName("A").build());
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(2).setName("B").build());
            //when
            List<BoardGame> boardGames = boardGameFacade.getAllBoardGames(null, 0, 2);
            //then
            assertAll(
                    () -> assertTrue(boardGames.get(0).getName().startsWith("A")),
                    () -> assertTrue(boardGames.get(1).getName().startsWith("B")),
                    () -> assertEquals(2, boardGames.size())
            );
        }

        @Test
        @DisplayName("sorted by name asc")
        void shouldReturnAllBoardGamesSortedByNameAsc() {
            //given
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(1).setName("A").build());
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(2).setName("B").build());

            //when
            List<BoardGame> boardGames = boardGameFacade.getAllBoardGames(BoardGameSortType.NAME_ASC, 0, 2);
            //then
            assertAll(
                    () -> assertTrue(boardGames.get(0).getName().startsWith("A")),
                    () -> assertTrue(boardGames.get(1).getName().startsWith("B")),
                    () -> assertEquals(2, boardGames.size())
            );
        }

        @Test
        @DisplayName("sorted by price dsc")
        void shouldReturnAllBoardGamesSortedByPriceDsc() {
            //given
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(1).setPricePerDay(5.0).build());
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(2).setPricePerDay(10.0).build());
            //when
            List<BoardGame> boardGames = boardGameFacade.getAllBoardGames(BoardGameSortType.PRICE_DSC, 0, 2);
            //then
            assertAll(
                    () -> assertEquals(10.0, boardGames.get(0).getPricePerDay()),
                    () -> assertEquals(5.0, boardGames.get(1).getPricePerDay()),
                    () -> assertEquals(2, boardGames.size())
            );
        }

        @Test
        @DisplayName("filtered by type")
        void shouldReturnAllBoardGamesFilteredByType() {
            //given
            Set<BoardGameType> type = new HashSet<>(Collections.singletonList(BoardGameType.BOARD_GAME));
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(1).setBoardGameDetailsTypes(type).build());
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(2).setBoardGameDetailsTypes(
                    new HashSet<>(Collections.singletonList(BoardGameType.DICE_GAME))).build());
            //when
            List<BoardGame> boardGames = boardGameFacade.getAllBoardGames(type, null, 0, 2);
            //then
            assertAll(
                    () -> assertEquals(type, boardGames.get(0).getDetails().getTypes()),
                    () -> assertEquals(1, boardGames.size())
            );
        }

        @Test
        @DisplayName("filtered by category")
        void shouldReturnAllBoardGamesFilteredByCategory() {
            //given
            Set<BoardGameCategory> category = new HashSet<>(Collections.singletonList(BoardGameCategory.FAMILY));
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(1).setBoardGameDetailsCategory(category).build());
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(2).setBoardGameDetailsCategory(
                    new HashSet<>(Collections.singletonList(BoardGameCategory.LOGIC))).build());
            //when
            List<BoardGame> boardGames = boardGameFacade.getAllBoardGames(null, category, 0, 2);
            //then
            assertAll(
                    () -> assertEquals(category, boardGames.get(0).getDetails().getCategories()),
                    () -> assertEquals(1, boardGames.size())
            );
        }

        @Test
        @DisplayName("offset by one")
        void shouldReturnAllBoardGamesOffsetByOne() {
            //given
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(1).build());
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(2).build());
            //when
            List<BoardGame> boardGames = boardGameFacade.getAllBoardGames(null, 1, 2);
            //then
            assertAll(
                    () -> assertEquals(1, boardGames.size())
            );
        }

        @Test
        @DisplayName("limited to one")
        void shouldReturnAllBoardGamesLimitedToOne() {
            //given
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(1).build());
            boardGameFacade.addBoardGame(boardGameBuilder.setBoardGameId(2).build());
            //when
            List<BoardGame> boardGames = boardGameFacade.getAllBoardGames(null, 0, 1);
            //then
            assertAll(
                    () -> assertEquals(1, boardGames.size())
            );
        }
    }
}
