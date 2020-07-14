package com.boardgamesworld.bgrental.renthistory.infrastructure.api;

import com.boardgamesworld.bgrental.renthistory.domain.RentHistory;
import com.boardgamesworld.bgrental.renthistory.domain.RentHistoryDto;
import com.boardgamesworld.bgrental.renthistory.domain.RentHistoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("")
public class RentHistoryController {

    private final RentHistoryFacade rentHistoryFacade;

    @GetMapping("/history")
    public ResponseEntity<List<RentHistoryDto>> getAllRentHistory() {
        List<RentHistory> rentHistory = rentHistoryFacade.getAllRentHistory();
        List<RentHistoryDto> rentHistoryDto;

        rentHistoryDto = rentHistory.stream()
                .map(rent -> new RentHistoryDto(
                        rent.getUserId(),
                        rent.getGameId(),
                        rent.getRentedDate(),
                        rent.getReturnedDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(rentHistoryDto);
    }

    @GetMapping(value = "/history", params = "userId")
    public ResponseEntity<List<RentHistoryDto>> getAllRentHistoryByUser(@RequestParam long userId) {
        List<RentHistory> rentHistory = rentHistoryFacade.getAllRentHistoryByUser(userId);
        List<RentHistoryDto> rentHistoryDto;

        rentHistoryDto = rentHistory.stream()
                .map(rent -> new RentHistoryDto(
                        rent.getUserId(),
                        rent.getGameId(),
                        rent.getRentedDate(),
                        rent.getReturnedDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(rentHistoryDto);
    }
}
