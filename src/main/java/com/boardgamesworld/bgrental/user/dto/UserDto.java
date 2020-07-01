package com.boardgamesworld.bgrental.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    private long userId;
    private String firstName;
    private String secondName;
    private String email;
    private String nick;
    private String password;

    public UserDto() {
    }

    public UserDto(long userId,
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