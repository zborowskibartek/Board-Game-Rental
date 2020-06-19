package com.boardgamesworld.bgrental.repositories;

import com.boardgamesworld.bgrental.model.BoardGame;
import com.boardgamesworld.bgrental.model.User;

import java.util.List;
import java.util.Set;

public interface UserRepository {

    List<User> getAllUser();

    User getUser(long userId);

    void addUser(User user);

    void updateUser(long userId, User updateUser);

    void deleteUser(long userId);

}
