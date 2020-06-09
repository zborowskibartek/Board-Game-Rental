package com.boardgamesworld.bgrental.entities;



public class BoardGame {

    private long boardGameId;
    private String name;
    private int pricePerDay;
    private BoardGameCondition condition;
    private String description;
    private boolean rented;

    public BoardGame() {
    }

    public BoardGame(long boardGameId, String name, int pricePerDay,
                     BoardGameCondition condition, String description, boolean rented) {
        this.boardGameId = boardGameId;
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.condition = condition;
        this.description = description;
        this.rented = rented;
    }

    public long getBoardGameId() {
        return boardGameId;
    }

    public void setBoardGameId(long boardGameId) {
        this.boardGameId = boardGameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public BoardGameCondition getCondition() {
        return condition;
    }

    public void setCondition(BoardGameCondition condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
