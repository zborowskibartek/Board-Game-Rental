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
@RequestMapping
public class RentHistoryController {

    private final RentHistoryFacade rentHistoryFacade;

    @GetMapping("/history")
    public ResponseEntity<RentHistoryResponse> getAllRentHistory() {
        List<RentHistory> rentHistory = rentHistoryFacade.getAllRentHistory();
        List<RentHistoryDto> rentHistoryDto = rentHistory.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new RentHistoryResponse(rentHistoryDto));
    }

    @GetMapping(value = "/history", params = "userId")
    public ResponseEntity<RentHistoryResponse> getAllRentHistoryByUser(@RequestParam long userId) {
        List<RentHistory> rentHistory = rentHistoryFacade.getAllRentHistoryByUser(userId);
        List<RentHistoryDto> rentHistoryDto = rentHistory.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new RentHistoryResponse(rentHistoryDto));
    }

    private RentHistoryDto toDto(RentHistory user) {
        return new RentHistoryDto(user.getUserId(),
                user.getBoardGameId(),
                user.getRentedDate(),
                user.getReturnedDate());
    }
}
