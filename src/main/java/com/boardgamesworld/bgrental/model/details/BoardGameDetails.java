package com.boardgamesworld.bgrental.model.details;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardGameDetails {

    private String description;
    private int minPlayers;
    private int maxPlayers;
    private String author;
    private String publisher;
    private List<BoardGameType> types;
    private List<BoardGameCategory> categories;


    public BoardGameDetails() {


    }

    public BoardGameDetails(String description) {
        this.description = description;
    }

    public BoardGameDetails(String description,
                            int minPlayers,
                            int maxPlayers,
                            String author,
                            String publisher,
                            List<BoardGameType> types,
                            List<BoardGameCategory> categories) {
        this.description = description;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.author = author;
        this.publisher = publisher;
        this.types = types;
        this.categories = categories;
    }
}
