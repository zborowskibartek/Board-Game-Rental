package com.boardgamesworld.bgrental.boardgame.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

class BoardGameSample {

    static BoardGame TOO_MANY_BONES = createBoardGame(1, "Too Many Bones", 14.0, false, BoardGameCondition.PERFECT,
            new BoardGameDetails("Too looong game!", 1, 5, "Who", "ChipTheoryGames",
                    new HashSet<>(Arrays.asList(BoardGameType.DICE_GAME, BoardGameType.CARD_GAME)),
                    new HashSet<>(Arrays.asList(BoardGameCategory.FANTASY, BoardGameCategory.COOPERATIVE))
            ));

    static BoardGame PATCHWORK = createBoardGame(2, "Patchwork", 6.0, true, BoardGameCondition.USED,
            new BoardGameDetails("Filler!", 2, 2, "Vlada", "Lacerta",
                    new HashSet<>(Collections.singletonList(BoardGameType.BOARD_GAME)),
                    new HashSet<>(Arrays.asList(BoardGameCategory.LOGIC, BoardGameCategory.STRATEGY))
            ));

    private static BoardGame createBoardGame(long boardGameId, String name, double pricePerDay, boolean rented, BoardGameCondition condition, BoardGameDetails details) {
        return BoardGameBuilder.create()
                .setBoardGameId(boardGameId)
                .setName(name)
                .setPricePerDay(pricePerDay)
                .isRented(rented)
                .setCondition(condition)
                .setDetails(details)
                .build();
    }


}
