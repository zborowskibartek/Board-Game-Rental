package com.boardgamesworld.bgrental.boardgame.domain;

import java.util.*;

import static com.boardgamesworld.bgrental.boardgame.domain.BoardGameCategory.FAMILY;

public class BoardGameBuilder {

    private long boardGameId = 22;
    private String name = "Test";
    private double pricePerDay = 10.0;
    private boolean rented = false;
    private BoardGameCondition condition = BoardGameCondition.USED;
    private BoardGameDetails details = BoardGameDetailsBuilder.any();

    public static BoardGame anyBoardGame() {
        return new BoardGameBuilder().build();
    }

    static BoardGameBuilder create() {
        return new BoardGameBuilder();
    }


    BoardGameBuilder setBoardGameId(long boardGameId) {
        this.boardGameId = boardGameId;
        return this;
    }

    BoardGameBuilder setName(String name) {
        this.name = name;
        return this;
    }

    BoardGameBuilder setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
        return this;
    }

    BoardGameBuilder isRented(boolean rented) {
        this.rented = rented;
        return this;
    }

    BoardGameBuilder setCondition(BoardGameCondition condition) {
        this.condition = condition;
        return this;
    }

    BoardGameBuilder setDetails(BoardGameDetails details) {
        this.details = details;
        return this;
    }

    BoardGameBuilder setBoardGameDetailsTypes(Set<BoardGameType> types) {
        this.details = BoardGameDetailsBuilder.create().setTypes(types).build();
        return this;
    }
    BoardGameBuilder setBoardGameDetailsCategory(Set<BoardGameCategory> categories) {
        this.details = BoardGameDetailsBuilder.create().setCategories(categories).build();
        return this;
    }

    BoardGame build() {
        return new BoardGame(boardGameId, name, pricePerDay, rented, condition, details);
    }



     static class BoardGameDetailsBuilder {

        private String description = "Test description!";
        private int minPlayers = 2;
        private int maxPlayers = 4;
        private String author = "Author";
        private String publisher = "Publisher";
        private Set<BoardGameType> types = new HashSet<>(Collections.singletonList(BoardGameType.CARD_GAME));
        private Set<BoardGameCategory> categories = new HashSet<>(Collections.singletonList(FAMILY));

        static BoardGameDetails any() {
            return new BoardGameDetailsBuilder().build();
        }

        static BoardGameDetailsBuilder create() {
            return new BoardGameDetailsBuilder();
        }

        BoardGameDetails build() {
            return new BoardGameDetails(description, minPlayers, maxPlayers, author, publisher, types, categories);
        }


        BoardGameDetailsBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        BoardGameDetailsBuilder setMinPlayers(int minPlayers) {
            this.minPlayers = minPlayers;
            return this;
        }

        BoardGameDetailsBuilder setMaxPlayers(int maxPlayers) {
            this.maxPlayers = maxPlayers;
            return this;
        }

        BoardGameDetailsBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        BoardGameDetailsBuilder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        BoardGameDetailsBuilder setTypes(Set<BoardGameType> types) {
            this.types = types;
            return this;
        }

        BoardGameDetailsBuilder setCategories(Set<BoardGameCategory> categories) {
            this.categories = categories;
            return this;
        }
    }

}
