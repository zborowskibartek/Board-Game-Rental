package com.boardgamesworld.bgrental.boardgame.infrastructure.api;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGameCategory;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BoardGameDetailsDto {

    private String description;
    private int minPlayers;
    private int maxPlayers;
    private String author;
    private String publisher;
    private List<BoardGameType> types;
    private List<BoardGameCategory> categories;

}
