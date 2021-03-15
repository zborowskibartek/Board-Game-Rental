package com.boardgamesworld.bgrental.boardgame.domain;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameFacadeTest {

    private BoardGameFacade boardGameFacade;
    private BoardGame tooManyBones;
    private BoardGame patchwork;

    @BeforeEach
    void init() {
        boardGameFacade = new BoardGameFacadeConfiguration().boardGameFacade(new BoardGameRepositoryInMemory());
        tooManyBones = new BoardGame(1, "Too Many Bones", 14.0, false, BoardGameCondition.PERFECT,
                new BoardGameDetails("Too looong game!", 1, 5, "Who", "ChipTheoryGames",
                        new ArrayList<>(Arrays.asList(BoardGameType.DICE_GAME, BoardGameType.CARD_GAME)),
                        new ArrayList<>(Arrays.asList(BoardGameCategory.FANTASY, BoardGameCategory.COOPERATIVE))
                ));
        patchwork = new BoardGame(2, "Patchwork", 6.0, true, BoardGameCondition.USED,
                new BoardGameDetails("Filler!", 2, 2, "Vlada", "Lacerta",
                        new ArrayList<>(Collections.singletonList(BoardGameType.BOARD_GAME)),
                        new ArrayList<>(Arrays.asList(BoardGameCategory.LOGIC, BoardGameCategory.STRATEGY))
                ));
    }

    @Nested
    @DisplayName("getBoardGame() should")
    class GetBoardGameTest {

        @Test
        @DisplayName("return board game")
        void shouldReturnBoardGameById() {
            //given
            boardGameFacade.addBoardGame(tooManyBones);
            //when
            BoardGame resultBoardGame = boardGameFacade.getBoardGame(1);
            //then
            assertEquals(tooManyBones, resultBoardGame);
        }

        @Test
        @DisplayName("return null if not present")
        void shouldReturnNullWhenBordGameNotPresent() {
            //given
            //when
            BoardGame resultBoardGame = boardGameFacade.getBoardGame(1);
            //then
            assertNull(resultBoardGame);
        }
    }

    @Nested
    @DisplayName("addBoardGame() should")
    class AddBoardGameTest {

        @Test
        @DisplayName("add board game")
        void shouldAddBoardGame() {
            //given
            //when
            boardGameFacade.addBoardGame(tooManyBones);
            //then
            BoardGame resultBoardGame = boardGameFacade.getBoardGame(1);
            assertEquals(tooManyBones, resultBoardGame);
        }

        @Nested
        @DisplayName("throw exception when")
        class InvalidBoardGameExceptionTest {

            @Test
            @DisplayName("name length is equal zero")
            void shouldThrowExceptionWhenNameLengthEqualZero() {
                //given
                tooManyBones = new BoardGame(1, "", 14.0, false, BoardGameCondition.PERFECT,
                        new BoardGameDetails("Too looong game!", 1, 5, "Who", "ChipTheoryGames",
                                new ArrayList<>(Arrays.asList(BoardGameType.DICE_GAME, BoardGameType.CARD_GAME)),
                                new ArrayList<>(Arrays.asList(BoardGameCategory.FANTASY, BoardGameCategory.COOPERATIVE))
                        ));
                //then
                assertThrows(InvalidBoardGameException.class, () -> boardGameFacade.addBoardGame(tooManyBones));
            }

            @Test
            @DisplayName("price per day is equal zero")
            void shouldThrowExceptionWhenPricePerDayEqualZero() {
                //given
                tooManyBones = new BoardGame(1, "Too Many Bones", 0, false, BoardGameCondition.PERFECT,
                        new BoardGameDetails("Too looong game!", 1, 5, "Who", "ChipTheoryGames",
                                new ArrayList<>(Arrays.asList(BoardGameType.DICE_GAME, BoardGameType.CARD_GAME)),
                                new ArrayList<>(Arrays.asList(BoardGameCategory.FANTASY, BoardGameCategory.COOPERATIVE))
                        ));
                //then
                assertThrows(InvalidBoardGameException.class, () -> boardGameFacade.addBoardGame(tooManyBones));
            }

            @Test
            @DisplayName("price per day is less than zero")
            void shouldThrowExceptionWhenPricePerDayLessThanZero() {
                //given
                tooManyBones = new BoardGame(1, "Too Many Bones", -3, false, BoardGameCondition.PERFECT,
                        new BoardGameDetails("Too looong game!", 1, 5, "Who", "ChipTheoryGames",
                                new ArrayList<>(Arrays.asList(BoardGameType.DICE_GAME, BoardGameType.CARD_GAME)),
                                new ArrayList<>(Arrays.asList(BoardGameCategory.FANTASY, BoardGameCategory.COOPERATIVE))
                        ));
                //then
                assertThrows(InvalidBoardGameException.class, () -> boardGameFacade.addBoardGame(tooManyBones));
            }

            @Test
            @DisplayName("not set any condition")
            void shouldThrowExceptionWhenNotSetAnyCondition() {
                //given
                tooManyBones = new BoardGame(1, "Too Many Bones", 14.0, false, null,
                        new BoardGameDetails("Too looong game!", 1, 5, "Who", "ChipTheoryGames",
                                new ArrayList<>(Arrays.asList(BoardGameType.DICE_GAME, BoardGameType.CARD_GAME)),
                                new ArrayList<>(Arrays.asList(BoardGameCategory.FANTASY, BoardGameCategory.COOPERATIVE))
                        ));
                //then
                assertThrows(InvalidBoardGameException.class, () -> boardGameFacade.addBoardGame(tooManyBones));
            }
        }
    }

    @Nested
    @DisplayName("getAllSortedBoardGames() should return list of board games")
    class BoardGameSortTest {
        @Test
        @DisplayName("sorted by name asc")
        void shouldReturnAllBoardGamesSortedByNameAsc() {
            //given
            boardGameFacade.addBoardGame(tooManyBones);
            boardGameFacade.addBoardGame(patchwork);
            //when
            List<BoardGame> boardGames = boardGameFacade.getAllSortedBoardGames(null, null, null, 0, 2);
            //then
            assertEquals(patchwork, boardGames.get(0));
            assertEquals(tooManyBones, boardGames.get(1));
        }

        @Test
        @DisplayName("sorted by price dsc")
        void shouldReturnAllBoardGamesSortedByPriceDsc() {
            //given
            boardGameFacade.addBoardGame(tooManyBones);
            boardGameFacade.addBoardGame(patchwork);
            //when
            List<BoardGame> boardGames = boardGameFacade.getAllSortedBoardGames(
                    BoardGameSortType.PRICE_DSC, null, null, 0, 2);
            //then
            assertEquals(tooManyBones, boardGames.get(0));
            assertEquals(patchwork, boardGames.get(1));
        }

        @Test
        @DisplayName("filtered by type")
        void shouldReturnAllBoardGamesFilteredByType() {
            //given
            boardGameFacade.addBoardGame(tooManyBones);
            boardGameFacade.addBoardGame(patchwork);
            //when
            List<BoardGame> boardGames = boardGameFacade.getAllSortedBoardGames(
                    null, new HashSet<>(Collections.singleton(BoardGameType.DICE_GAME)), null, 0, 2);
            //then
            assertEquals(1, boardGames.size());
        }

        @Test
        @DisplayName("filtered by category")
        void shouldReturnAllBoardGamesFilteredByCategory() {
            //given
            boardGameFacade.addBoardGame(tooManyBones);
            boardGameFacade.addBoardGame(patchwork);
            //when
            List<BoardGame> boardGames = boardGameFacade.getAllSortedBoardGames(
                    null, null, new HashSet<>(Collections.singleton(BoardGameCategory.LOGIC)), 0, 2);
            //then
            assertEquals(1, boardGames.size());
        }

        @Test
        @DisplayName("offset by one")
        void shouldReturnAllBoardGamesOffsetByOne() {
            //given
            boardGameFacade.addBoardGame(tooManyBones);
            boardGameFacade.addBoardGame(patchwork);
            //when
            List<BoardGame> boardGames = boardGameFacade.getAllSortedBoardGames(
                    null, null, null, 1, 10);
            //then
            assertAll(
                    () -> assertEquals(tooManyBones, boardGames.get(0)),
                    () -> assertEquals(1, boardGames.size())
            );

        }

        @Test
        @DisplayName("limited to one")
        void shouldReturnAllBoardGamesLimitedToOne() {
            //given
            boardGameFacade.addBoardGame(tooManyBones);
            boardGameFacade.addBoardGame(patchwork);
            //when
            List<BoardGame> boardGames = boardGameFacade.getAllSortedBoardGames(
                    null, null, null, 0, 1);
            //then
            assertEquals(1, boardGames.size());
        }
    }

    @Test
    @Tag("Assumption")
    void checkAssumption() {
        Assumptions.assumeTrue(true);
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("deleteBoardGame() should remove board game")
    void shouldDeleteBoardGame() {
        //given
        boardGameFacade.addBoardGame(tooManyBones);
        //when
        boardGameFacade.deleteBoardGame(1);
        //then
        BoardGame result = boardGameFacade.getBoardGame(1);
        assertNull(result);
    }

    @Test
    @DisplayName("updateBoardGame() should update board game")
    void shouldUpdateBoardGame() {
        //given
        boardGameFacade.addBoardGame(tooManyBones);
        //when
        BoardGame updatedBoardGame = new BoardGame(1, "Too Many Bones", 100.0, false, BoardGameCondition.PERFECT,
                new BoardGameDetails("GRAALL must be expensive!", 1, 5, "Who", "ChipTheoryGames",
                        new ArrayList<>(Arrays.asList(BoardGameType.DICE_GAME, BoardGameType.CARD_GAME)),
                        new ArrayList<>(Arrays.asList(BoardGameCategory.FANTASY, BoardGameCategory.COOPERATIVE))
                ));
        boardGameFacade.updateBoardGame(1, updatedBoardGame);
        //then
        assertEquals(updatedBoardGame, boardGameFacade.getBoardGame(1));
    }
}