package com.boardgamesworld.bgrental.renthistory.infrastructure.api;

import com.boardgamesworld.bgrental.renthistory.domain.RentHistory;
import com.boardgamesworld.bgrental.renthistory.domain.RentHistoryDto;
import com.boardgamesworld.bgrental.renthistory.domain.RentHistoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .map(rentHis -> new RentHistoryDto(
                        rentHis.getUserId(),
                        rentHis.getGameId(),
                        rentHis.getRentedDate(),
                        rentHis.getReturnedDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(rentHistoryDto);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<RentHistoryDto>> getAllRentHistoryByUser(@PathVariable long userId) {
        List<RentHistory> rentHistory = rentHistoryFacade.getAllRentHistoryByUser(userId);
        List<RentHistoryDto> rentHistoryDto;

        rentHistoryDto = rentHistory.stream()
                .map(rentHis -> new RentHistoryDto(
                        rentHis.getUserId(),
                        rentHis.getGameId(),
                        rentHis.getRentedDate(),
                        rentHis.getReturnedDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(rentHistoryDto);
    }
}
