package com.boardgamesworld.bgrental.controllers;

import com.boardgamesworld.bgrental.model.BoardGame;
import com.boardgamesworld.bgrental.services.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boardgames")
public class BoardGameController {

    private BoardGameService boardGameService;

    @Autowired
    public BoardGameController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @GetMapping("")
    public List<BoardGame> getAllBoardGames() {
        return boardGameService.getAllBoardGames();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBoardGame(@RequestBody BoardGame boardGame) {
        boardGameService.addBoardGame(boardGame);
    }

    @GetMapping("/{boardGameId}")
    public BoardGame getBoardGameById(@PathVariable("boardGameId") long boardGameId) {
        return boardGameService.getBoardGame(boardGameId);
    }

    @DeleteMapping("/{boardGameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoardGame(@PathVariable long boardGameId) {
        boardGameService.deleteBoardGame(boardGameId);
    }

    /* @PutMapping("/{boardGameId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBoardGame(@PathVariable long boardGameId, @RequestBody BoardGame boardGameWithUpdatedProperties) {
        boardGameService.updateBoardGame(boardGameId, boardGameWithUpdatedProperties);
    }*/
}
