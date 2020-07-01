package com.boardgamesworld.bgrental.user.domain;

import lombok.Getter;

@Getter
class User {

    private long userId;
    private String firstName;
    private String secondName;
    private String email;
    private String nick;
    private String password;

     User(long userId,
                String firstName,
                String secondName,
                String email,
                String nick,
                String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.nick = nick;
        this.password = password;
    }
}