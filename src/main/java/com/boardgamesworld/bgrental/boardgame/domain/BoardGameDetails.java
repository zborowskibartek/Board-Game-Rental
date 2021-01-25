package com.boardgamesworld.bgrental.boardgame.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BoardGameDetails {

    private final String description;
    private final int minPlayers;
    private final int maxPlayers;
    private final String author;
    private final String publisher;
    private final List<BoardGameType> types;
    private final List<BoardGameCategory> categories;

}
