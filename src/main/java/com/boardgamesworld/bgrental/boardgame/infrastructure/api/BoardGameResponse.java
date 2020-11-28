package com.boardgamesworld.bgrental.boardgame.infrastructure.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardGameResponse {

    private List<BoardGameDto> boardGames;
    private int totalCount;

    public BoardGameResponse(List<BoardGameDto> boardGamesDto) {
        this.boardGames = boardGamesDto;
        this.totalCount = boardGamesDto.size();
    }
}
