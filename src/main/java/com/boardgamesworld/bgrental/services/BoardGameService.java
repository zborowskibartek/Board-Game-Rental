package com.boardgamesworld.bgrental.services;

import com.boardgamesworld.bgrental.repositories.interfaces.BoardGameRepository;
import com.boardgamesworld.bgrental.model.BoardGame;
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

    public BoardGame getBoardGame(long boardGameId) {
        return boardGameRepository.getBoardGame(boardGameId);
    }

    public void addBoardGame(BoardGame boardGame) {
        boardGameRepository.addBoardGame(boardGame);
    }

    public void updateBoardGame(long boardGameId, BoardGame updatedBoardGame) {
        boardGameRepository.updateBoardGame(boardGameId, updatedBoardGame);
    }

    public void deleteBoardGame(long boardGameId) {
        boardGameRepository.deleteBoardGame(boardGameId);
    }

    public void changeBoardGameStatusAsRented(long boardGameId){
        BoardGame boardGameToChangeStatus = getBoardGame(boardGameId);
        if (!boardGameToChangeStatus.isRented()){
            boardGameToChangeStatus.setRented(true);
        } else {
            throw new IllegalStateException("Board game is already rented! Choose another one.");
        }
    }

    public void changeBoardGameStatusAsReturned(long boardGameId){
        BoardGame boardGameToChangeStatus = getBoardGame(boardGameId);
        if (boardGameToChangeStatus.isRented()){
            boardGameToChangeStatus.setRented(false);
        } else {
            throw new IllegalStateException("Board game is already returned! Choose another one.");
        }
    }


}
