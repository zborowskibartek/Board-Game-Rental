package com.boardgamesworld.bgrental.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private final long userId;
    private final String firstName;
    private final String secondName;
    private final String email;
    private final String nick;
    private final String password;

}