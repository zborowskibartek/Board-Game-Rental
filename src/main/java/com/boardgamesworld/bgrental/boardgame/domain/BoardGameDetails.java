package com.boardgamesworld.bgrental.boardgame.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BoardGameDetails {

    private final String description;
    private final int minPlayers;
    private final int maxPlayers;
    private final String author;
    private final String publisher;
    private final Set<BoardGameType> types;
    private final Set<BoardGameCategory> categories;
}
