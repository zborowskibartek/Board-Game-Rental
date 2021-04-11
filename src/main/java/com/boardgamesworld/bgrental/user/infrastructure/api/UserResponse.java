package com.boardgamesworld.bgrental.user.infrastructure.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private List<UserDto> users;

}
