package com.boardgamesworld.bgrental.user.domain;

public class UserBuilder {

    private long userId = 5;
    private String firstName = "FirstName";
    private String secondName = "SecondName";
    private String email = "test@gmail.com";
    private String nick = "testnick123";
    private String password = "boo";

    public static User anyUser() {
        return new UserBuilder().build();
    }

    public static UserBuilder create() {
        return new UserBuilder();
    }

    public User build() {
        return new User(userId, firstName, secondName, email, nick, password);
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
