package com.boardgamesworld.bgrental.repositories.interfaces;

import com.boardgamesworld.bgrental.model.BoardGame;
import com.boardgamesworld.bgrental.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUser();

    User getUser(long userId);

    void addUser(User user);

    void updateUser(long userId, User updateUser);

    void deleteUser(long userId);

    List<BoardGame> getAllAtPresentRentedBoardGamesByUser(long userId);

    List<BoardGame> getAllRentedBoardGamesHistoryByUser(long userId);
}
