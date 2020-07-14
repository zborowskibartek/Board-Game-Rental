package com.boardgamesworld.bgrental.sort.infrastructure.api;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import com.boardgamesworld.bgrental.sort.domain.BoardGameSortFacade;
import com.boardgamesworld.bgrental.sort.domain.InvalidSortException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/boardgames")
public class BoardGameSortController {

    private final BoardGameSortFacade boardGameSortFacade;

    @GetMapping(params = {"sort", "order"})
    public ResponseEntity<List<BoardGame>> getSortedBoardGames(@RequestParam String sort, @RequestParam String order) {
        try {
            switch (sort) {
                case "name":
                    return ResponseEntity.ok(boardGameSortFacade.getSortedBoardGamesByName(order));
                case "price":
                    return ResponseEntity.ok(boardGameSortFacade.getSortedBoardGamesByPrice(order));
                default:
                    throw new InvalidSortException("Try with: \"name\" or \"price\" as a sort parameter!");
            }
        } catch (InvalidSortException exception) {
            return ResponseEntity.unprocessableEntity().header("Error", exception.getMessage()).build();
        }
    }

}
