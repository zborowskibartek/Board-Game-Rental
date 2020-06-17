package com.boardgamesworld.bgrental.controllers;

import com.boardgamesworld.bgrental.repositories.hashmap.BoardGameHashMapRepository;
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

    @GetMapping("/check")
    public long getId() {
        return BoardGameHashMapRepository.getUniqueIdGenerator();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBoardGame(@RequestBody BoardGame boardGame) {
                boardGameService.addBoardGame(boardGame);
    }

    @PutMapping("/{boardGameId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBoardGame(@PathVariable int boardGameId, @RequestBody BoardGame updatedBoardGame) {
        boardGameService.updateBoardGame(boardGameId, updatedBoardGame);
    }

    @GetMapping("/{boardGameId}")
    public BoardGame getBoardGameById(@PathVariable int boardGameId) {
        return boardGameService.getBoardGame(boardGameId);
    }

    @DeleteMapping("/{boardGameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoardGame(@PathVariable long boardGameId) {
        boardGameService.deleteBoardGame(boardGameId);
    }
}
