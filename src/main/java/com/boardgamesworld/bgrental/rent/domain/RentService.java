package com.boardgamesworld.bgrental.rent.domain;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameFacade;
import com.boardgamesworld.bgrental.renthistory.domain.RentHistory;
import com.boardgamesworld.bgrental.renthistory.domain.RentHistoryFacade;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
class RentService {

    private final RentRepository rentRepository;
    private final RentValidator rentValidator;
    private final BoardGameFacade boardGameFacade;
    private final RentHistoryFacade rentHistoryFacade;

    void rentBoardGame(long boardGameId, long userId) {
        rentValidator.validateRent(boardGameId, userId);
        addRentToRepository(boardGameId, userId);
        changeBoardGameStatusAsRented(boardGameId);
    }

    void returnBoardGame(long boardGameId) {
        rentValidator.validateReturn(boardGameId);
        addRentToRentHistoryRepository(boardGameId);
        removeRentFromRepository(boardGameId);
        changeBoardGameStatusAsReturned(boardGameId);
    }

    List<Rent> getAllRents() {
        return rentRepository.getAllRents();
    }

    List<Rent> getAllRentsByUser(long userId) {
        return rentRepository.getAllRentsByUser(userId);
    }

    List<BoardGame> getAllRentBoardGames() {
        List<BoardGame> boardGames = new ArrayList<>();
        rentRepository.getAllRentBoardGameIds()
                .forEach(boardGameId -> boardGames.add(boardGameFacade.getBoardGame(boardGameId)));
        return boardGames;
    }

    List<BoardGame> getAllRentBoardGamesByUser(long userId) {
        List<BoardGame> boardGames = new ArrayList<>();
        rentRepository.getAllRentBoardGameIdsByUser(userId)
                .forEach(boardGameId -> boardGames.add(boardGameFacade.getBoardGame(boardGameId)));
        return boardGames;
    }

    private void removeRentFromRepository(long boardGameId) {
        rentRepository.removeRent(boardGameId);
    }

    private void addRentToRepository(long boardGameId, long userId) {
        Rent rent = new Rent(boardGameId, userId, LocalDateTime.now());
        rentRepository.addRent(rent);
    }

    private void changeBoardGameStatusAsRented(long boardGameId) {
        setBoardGameRentStatus(boardGameId, true);
    }

    private void changeBoardGameStatusAsReturned(long boardGameId) {
        setBoardGameRentStatus(boardGameId, false);
    }

    private void setBoardGameRentStatus(long boardGameId, boolean rented) {
        BoardGame boardGame = boardGameFacade.getBoardGame(boardGameId);

        boardGameFacade.updateBoardGame(boardGameId,
                new BoardGame(boardGameId,
                        boardGame.getName(),
                        boardGame.getPricePerDay(),
                        rented,
                        boardGame.getCondition(),
                        boardGame.getDetails()));
    }

    private void addRentToRentHistoryRepository(long boardGameId) {
        Rent rent = rentRepository.getRent(boardGameId);
        rentHistoryFacade.addRentHistory(
                new RentHistory(rent.getUserId(),
                        rent.getGameId(),
                        rent.getRentedDate(),
                        LocalDateTime.now()));
    }
}
