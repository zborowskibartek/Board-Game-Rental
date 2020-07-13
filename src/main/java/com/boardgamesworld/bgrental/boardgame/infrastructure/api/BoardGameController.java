package com.boardgamesworld.bgrental.boardgame.infrastructure.api;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.boardgame.domain.BoardGameFacade;
import com.boardgamesworld.bgrental.boardgame.domain.InvalidBoardGameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boardgames")
public class BoardGameController {

    private BoardGameFacade boardGameFacade;
    private Logger logger = LoggerFactory.getLogger(BoardGameController.class);

    public BoardGameController(BoardGameFacade boardGameFacade) {
        this.boardGameFacade = boardGameFacade;
    }

    @GetMapping
    public ResponseEntity<List<BoardGameDto>> getAllBoardGames() {
        List<BoardGame> boardGames = boardGameFacade.getAllBoardGames();
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

    @PostMapping
    public ResponseEntity addBoardGame(@RequestBody BoardGameDto boardGameDto) {
        try {
            BoardGame boardGame = new BoardGame(
                    boardGameDto.getBoardGameId(),
                    boardGameDto.getName(),
                    boardGameDto.getPricePerDay(),
                    boardGameDto.isRented(),
                    boardGameDto.getCondition(),
                    boardGameDto.getDetails()
            );

            boardGameFacade.addBoardGame(boardGame);
            URI uri = URI.create("boardgames/" + boardGame.getBoardGameId());
            logger.info("Add board game!");

            return ResponseEntity.created(uri).build();
        } catch (InvalidBoardGameException exceptions) {
            return ResponseEntity.badRequest().body(exceptions.getBoardGameExceptions());
        }
    }

    @GetMapping("/{boardGameId}")
    public ResponseEntity<BoardGameDto> getBoardGameById(@PathVariable long boardGameId) {
        BoardGame boardGame = boardGameFacade.getBoardGame(boardGameId);

        if (boardGame != null) {
            BoardGameDto boardGameDto = new BoardGameDto(
                    boardGame.getBoardGameId(),
                    boardGame.getName(),
                    boardGame.getPricePerDay(),
                    boardGame.isRented(),
                    boardGame.getCondition(),
                    boardGame.getDetails()
            );

            return ResponseEntity.ok(boardGameDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{boardGameId}")
    public ResponseEntity deleteBoardGame(@PathVariable long boardGameId) {
        boardGameFacade.deleteBoardGame(boardGameId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{boardGameId}")
    public ResponseEntity updateBoardGame(@PathVariable long boardGameId, @RequestBody BoardGameDto updatedBoardGameDto) {
        BoardGame boardGame = boardGameFacade.getBoardGame(boardGameId);

        if (boardGame != null) {
            BoardGame updatedBoardGame = new BoardGame(
                    boardGameId,
                    updatedBoardGameDto.getName(),
                    updatedBoardGameDto.getPricePerDay(),
                    updatedBoardGameDto.isRented(),
                    updatedBoardGameDto.getCondition(),
                    updatedBoardGameDto.getDetails());
            try {
                boardGameFacade.updateBoardGame(boardGameId, updatedBoardGame);
                URI uri = URI.create("boardgames/" + boardGameId);
                logger.info("Updated board game " + boardGameId + "!");

                return ResponseEntity.created(uri).body(updatedBoardGame);
            } catch (InvalidBoardGameException exceptions) {
                return ResponseEntity.badRequest().body(exceptions.getBoardGameExceptions());
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
