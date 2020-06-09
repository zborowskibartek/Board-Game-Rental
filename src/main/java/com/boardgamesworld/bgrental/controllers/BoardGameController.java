package com.boardgamesworld.bgrental.controllers;

import com.boardgamesworld.bgrental.entities.BoardGame;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boardgames")
public class BoardGameController {

    private List<BoardGame> boardGameList;

    public BoardGameController() {
    }

    public BoardGameController(List<BoardGame> boardGameList) {
        this.boardGameList = boardGameList;
    }

    public List<BoardGame> getBoardGameList() {
        return boardGameList;
    }

    public void setBoardGameList(List<BoardGame> boardGameList) {
        this.boardGameList = boardGameList;
    }

    @GetMapping("/{index}")
    public BoardGame getBoardGame(int index){
        return boardGameList.get(index);
    }








}
