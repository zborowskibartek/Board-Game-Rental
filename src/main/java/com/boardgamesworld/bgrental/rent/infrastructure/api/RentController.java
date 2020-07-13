package com.boardgamesworld.bgrental.rent.infrastructure.api;


import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.infrastructure.api.BoardGameDto;
import com.boardgamesworld.bgrental.rent.domain.InvalidRentException;
import com.boardgamesworld.bgrental.rent.domain.Rent;
import com.boardgamesworld.bgrental.rent.domain.RentFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class RentController {

    private RentFacade rentFacade;
    private Logger logger = LoggerFactory.getLogger(RentController.class);

    public RentController(RentFacade rentFacade) {
        this.rentFacade = rentFacade;
    }

    @GetMapping("/rents")
    public ResponseEntity<List<RentDto>> getAllRents() {
        List<Rent> rents = rentFacade.getAllRents();
        List<RentDto> rentsDto;

        rentsDto = rents.stream()
                .map(rent -> new RentDto(rent.getGameId(), rent.getUserId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(rentsDto);
    }

    @GetMapping("/rents/{userId}")
    public ResponseEntity<List<RentDto>> getAllRentsByUser(@PathVariable long userId) {
        List<Rent> rents = rentFacade.getAllRentsByUser(userId);
        List<RentDto> rentsDto;

        rentsDto = rents.stream()
                .map(rent -> new RentDto(rent.getGameId(), rent.getUserId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(rentsDto);
    }

    @GetMapping("/rents/boardgames")
    public ResponseEntity<List<BoardGameDto>> getAllRentBoardGames() {
        List<BoardGame> boardGames = rentFacade.getAllRentBoardGames();
        List<BoardGameDto> boardGamesDto;

        boardGamesDto = boardGames.stream()
                .map(boardGame -> new BoardGameDto(
                        boardGame.getBoardGameId(),
                        boardGame.getName(),
                        boardGame.getPricePerDay(),
                        boardGame.isRented(),
                        boardGame.getCondition(),
                        boardGame.getDetails()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(boardGamesDto);
    }

    @GetMapping("/rents/boardgames/{userId}")
    public ResponseEntity<List<BoardGameDto>> getAllRentBoardGamesByUser(@PathVariable long userId) {
        List<BoardGame> boardGames = rentFacade.getAllRentBoardGamesByUser(userId);
        List<BoardGameDto> boardGamesDto;

        boardGamesDto = boardGames.stream()
                .map(boardGame -> new BoardGameDto(
                        boardGame.getBoardGameId(),
                        boardGame.getName(),
                        boardGame.getPricePerDay(),
                        boardGame.isRented(),
                        boardGame.getCondition(),
                        boardGame.getDetails()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(boardGamesDto);
    }

    @PostMapping("/rent")
    public ResponseEntity rentBoardGame(@RequestParam long boardGameId, @RequestParam long userId) {
        try {
            rentFacade.rentBoardGame(boardGameId, userId);
            URI uri = URI.create("/rent/" + boardGameId);
            logger.info("Rent board game!");

            return ResponseEntity.created(uri).build();
        } catch (InvalidRentException exception) {
            return ResponseEntity.badRequest().body(exception.getRentExceptions());
        }
    }

    @PutMapping("/return")
    public ResponseEntity returnBoardGame(@RequestParam long boardGameId) {
        try {
            rentFacade.returnBoardGame(boardGameId);
            logger.info("Returned board game!");

            return ResponseEntity.ok().build();
        } catch (InvalidRentException exception) {
            return ResponseEntity.badRequest().body(exception.getRentExceptions());
        }
    }
}