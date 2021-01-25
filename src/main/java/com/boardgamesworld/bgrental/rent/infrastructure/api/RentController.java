package com.boardgamesworld.bgrental.rent.infrastructure.api;


import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameFacade;
import com.boardgamesworld.bgrental.boardgame.infrastructure.api.BoardGameDto;
import com.boardgamesworld.bgrental.boardgame.infrastructure.api.BoardGameMapper;
import com.boardgamesworld.bgrental.boardgame.infrastructure.api.BoardGameResponse;
import com.boardgamesworld.bgrental.rent.domain.InvalidRentException;
import com.boardgamesworld.bgrental.rent.domain.Rent;
import com.boardgamesworld.bgrental.rent.domain.RentFacade;
import com.boardgamesworld.bgrental.user.domain.UserFacade;
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

    private final RentFacade rentFacade;
    private final UserFacade userFacade;
    private final BoardGameFacade boardGameFacade;
    private final Logger logger = LoggerFactory.getLogger(RentController.class);

    public RentController(RentFacade rentFacade, UserFacade userFacade, BoardGameFacade boardGameFacade) {
        this.rentFacade = rentFacade;
        this.userFacade = userFacade;
        this.boardGameFacade = boardGameFacade;
    }

    @PostMapping("/rent")
    public ResponseEntity rentBoardGame(@RequestParam long boardGameId, @RequestParam long userId) {
        try {
            rentFacade.rentBoardGame(boardGameId, userId);
        } catch (InvalidRentException exception) {
            return ResponseEntity.badRequest().body(exception.getRentExceptions());
        }
        URI uri = URI.create("/rent/" + boardGameId);
        logger.info("User with id: " + userId + " rent board game with id: " + boardGameId + " !");
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/return")
    public ResponseEntity returnBoardGame(@RequestParam long boardGameId) {
        try {
            rentFacade.returnBoardGame(boardGameId);
        } catch (InvalidRentException exception) {
            return ResponseEntity.badRequest().body(exception.getRentExceptions());
        }
        logger.info("Returned board game with id " + boardGameId + " !");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rents")
    public ResponseEntity<RentResponse> getAllRents() {
        List<Rent> rents = rentFacade.getAllRents();
        List<RentDto> rentsDto = rents.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new RentResponse(rentsDto));
    }

    @GetMapping(value = "/rents", params = "userId")
    public ResponseEntity<RentResponse> getAllRentsByUser(@RequestParam long userId) {
        List<Rent> rents = rentFacade.getAllRentsByUser(userId);
        List<RentDto> rentsDto = rents.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new RentResponse(rentsDto));
    }

    @GetMapping("/rents/boardgames")
    public ResponseEntity<BoardGameResponse> getAllRentBoardGames() {
        List<BoardGame> boardGames = rentFacade.getAllRentBoardGames();
        List<BoardGameDto> boardGamesDto = boardGames.stream()
                .map(BoardGameMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new BoardGameResponse(boardGamesDto));
    }

    @GetMapping(value = "/rents/boardgames", params = "userId")
    public ResponseEntity<BoardGameResponse> getAllRentBoardGamesByUser(@RequestParam long userId) {
        List<BoardGame> boardGames = rentFacade.getAllRentBoardGamesByUser(userId);
        List<BoardGameDto> boardGamesDto = boardGames.stream()
                .map(BoardGameMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new BoardGameResponse(boardGamesDto));
    }

    private RentDto toDto(Rent rent) {
        return new RentDto(rent.getGameId(), rent.getUserId());
    }
}