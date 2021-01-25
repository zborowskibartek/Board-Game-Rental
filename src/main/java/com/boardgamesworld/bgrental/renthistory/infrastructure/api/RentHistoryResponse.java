package com.boardgamesworld.bgrental.renthistory.infrastructure.api;

import com.boardgamesworld.bgrental.renthistory.domain.RentHistoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentHistoryResponse {

    private List<RentHistoryDto> rentHistories;

}
