package com.boardgamesworld.bgrental.user.infrastructure.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long userId;
    private String firstName;
    private String secondName;
    private String email;
    private String nick;
    private String password;
}