package com.boardgamesworld.bgrental.sort.infrastructure.api;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameCategory;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameType;
import com.boardgamesworld.bgrental.boardgame.infrastructure.api.BoardGameDto;
import com.boardgamesworld.bgrental.boardgame.infrastructure.api.BoardGameMapper;
import com.boardgamesworld.bgrental.boardgame.infrastructure.api.BoardGameResponse;
import com.boardgamesworld.bgrental.sort.domain.BoardGameSortFacade;
import com.boardgamesworld.bgrental.sort.domain.BoardGameSortType;
import com.boardgamesworld.bgrental.sort.domain.InvalidSortException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/boardgames/test")
public class BoardGameSortController {

    private final BoardGameSortFacade boardGameSortFacade;

    @GetMapping
    public ResponseEntity<BoardGameResponse> getSortedBoardGames(@RequestParam(value = "sort", required = false) BoardGameSortType sort,
                                                                 @RequestParam(value = "type", required = false) List<BoardGameType> types,
                                                                 @RequestParam(value = "category", required = false) BoardGameCategory category) {
        List<BoardGame> boardGames;
        try {
            boardGames = boardGameSortFacade.getSortedBoardGames(sort, types, category);
        } catch (InvalidSortException exception) {
            return ResponseEntity.unprocessableEntity().header("Error", exception.getMessage()).build();
        }
        List<BoardGameDto> boardGameDto = boardGames.stream()
                .map(BoardGameMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new BoardGameResponse(boardGameDto));
    }
}