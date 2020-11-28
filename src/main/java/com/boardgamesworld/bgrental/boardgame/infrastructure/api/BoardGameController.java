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
public class  BoardGameController {

    private final BoardGameFacade boardGameFacade;
    private final Logger logger = LoggerFactory.getLogger(BoardGameController.class);

    public BoardGameController(BoardGameFacade boardGameFacade) {
        this.boardGameFacade = boardGameFacade;
    }

    @GetMapping
    public ResponseEntity<BoardGameResponse> getAllBoardGames() {
        List<BoardGame> boardGames = boardGameFacade.getAllBoardGames();
        List<BoardGameDto> boardGamesDto = boardGames.stream()
                .map(BoardGameMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new BoardGameResponse(boardGamesDto));
    }

    @PostMapping
    public ResponseEntity addBoardGame(@RequestBody BoardGameDto boardGameDto) {
        BoardGame boardGame = BoardGameMapper.fromDto(boardGameDto);
        try {
            boardGameFacade.addBoardGame(boardGame);
        } catch (InvalidBoardGameException exceptions) {
            return ResponseEntity.badRequest().body(exceptions.getBoardGameErrors());
        }
        URI uri = URI.create("boardgames/" + boardGame.getBoardGameId());
        logger.info("Add new board game " + boardGame.getName() + "!");
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{boardGameId}")
    public ResponseEntity<BoardGameDto> getBoardGameById(@PathVariable long boardGameId) {
        BoardGame boardGame = boardGameFacade.getBoardGame(boardGameId);
        if (boardGame == null) {
            return ResponseEntity.notFound().build();
        } else {
            BoardGameDto boardGameDto = BoardGameMapper.toDto(boardGame);
            return ResponseEntity.ok(boardGameDto);
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
        if (boardGame == null) {
            return ResponseEntity.notFound().build();
        } else {
            BoardGame updatedBoardGame = BoardGameMapper.fromDto(updatedBoardGameDto);
            try {
                boardGameFacade.updateBoardGame(boardGameId, updatedBoardGame);
            } catch (InvalidBoardGameException exceptions) {
                return ResponseEntity.badRequest().body(exceptions.getBoardGameErrors());
            }
            URI uri = URI.create("boardgames/" + boardGameId);
            logger.info("Updated board game " + updatedBoardGame.getName() + "!");
            return ResponseEntity.created(uri).body(updatedBoardGame);
        }
    }
}
