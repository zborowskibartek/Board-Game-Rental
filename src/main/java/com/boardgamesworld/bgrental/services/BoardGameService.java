package com.boardgamesworld.bgrental.services;

import com.boardgamesworld.bgrental.dao.BoardGameRepository;
import com.boardgamesworld.bgrental.entities.BoardGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardGameService {

    private BoardGameRepository boardGameRepository;

    @Autowired
    public BoardGameService(BoardGameRepository boardGameRepository) {
        this.boardGameRepository = boardGameRepository;
    }

    public List<BoardGame> getAllBoardGames() {
        return boardGameRepository.getAllBoardGames();
    }

    public BoardGame getBoardGame(int boardGameId) {
        return boardGameRepository.getBoardGame(boardGameId);
    }

    public void addBoardGame(BoardGame boardGame) {
        boardGameRepository.addBoardGame(boardGame);
    }

    public void updateBoardGame(int boardGameId, BoardGame updatedBoardGame) {
        boardGameRepository.updateBoardGame(boardGameId, updatedBoardGame);
    }

    public void deleteBoardGame(int boardGameId) {
        boardGameRepository.deleteBoardGame(boardGameId);
    }

}
