package com.boardgamesworld.bgrental.user.domain;


import java.util.List;

public interface UserRepository {

    List<User> getAllUser();

    User getUser(long userId);

    void addUser(User user);

    void updateUser(long userId, User updatedUser);

    void deleteUser(long userId);
}
