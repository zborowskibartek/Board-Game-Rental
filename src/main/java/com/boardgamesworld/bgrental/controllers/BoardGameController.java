package com.boardgamesworld.bgrental.controllers;

import com.boardgamesworld.bgrental.entities.BoardGame;
import com.boardgamesworld.bgrental.services.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boardgames")
public class BoardGameController {

    @Autowired
    private BoardGameService boardGameService;

    @GetMapping("")
    public List<BoardGame> getAllBoardGames() {
        return boardGameService.getAllBoardGames();
    }

    @PostMapping("")
    public void addBoardGame(@RequestBody BoardGame boardGame) {
        boardGameService.addBoardGame(boardGame);
    }

    @PutMapping("/{boardGameId}")
    public void updateBoardGame(@PathVariable int boardGameId, @RequestBody BoardGame updatedBoardGame) {
        boardGameService.updateBoardGame(boardGameId, updatedBoardGame);
    }

    @GetMapping("/{boardGameId}")
    public BoardGame getBoardGameById(@PathVariable int boardGameId) {
        return boardGameService.getBoardGame(boardGameId);
    }

    @DeleteMapping("/{boardGameId}")
    public void deleteBoardGame(@PathVariable int boardGameId) {
        boardGameService.deleteBoardGame(boardGameId);
    }
}
