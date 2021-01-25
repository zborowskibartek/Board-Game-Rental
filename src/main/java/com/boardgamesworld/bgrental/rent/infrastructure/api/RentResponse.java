package com.boardgamesworld.bgrental.rent.infrastructure.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentResponse {

    public List<RentDto> rentList;

}
