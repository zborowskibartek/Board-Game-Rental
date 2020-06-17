package com.boardgamesworld.bgrental.repositories.hashmap;

import com.boardgamesworld.bgrental.model.BoardGame;
import com.boardgamesworld.bgrental.model.User;
import com.boardgamesworld.bgrental.repositories.interfaces.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;


@Primary
@Repository
public class UserHashMapRepository implements UserRepository {

    private Map<Long, User> users;

    public UserHashMapRepository() {
        users = new HashMap<>();

        User user = new User(1, "Bartek", "Zboro",
                "email@example.com", "bartas95", "password123", new HashSet<>(), new ArrayList<>());
        users.put(user.getUserId(), user);
    }

    @Override
    public List<User> getAllUser() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUser(long userId) {
        return users.get(userId);
    }

    @Override
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public void deleteUser(long userId) {
        if (isUserInRepository(userId)) {
            users.remove(userId);
        }
    }

    @Override
    public Set<BoardGame> getAllAtPresentRentedBoardGamesByUser(long userId) {
        User user = users.get(userId);
        return user.getAtPresentRentedBoardGames();
    }

    @Override
    public List<BoardGame> getAllRentedBoardGamesHistoryByUser(long userId) {
        User user = users.get(userId);
        return user.getRentedBoardGamesHistory();
    }

    private boolean isUserInRepository(long userId) {
        if (users.containsKey(userId)) {
            return true;
        }
        throw new IllegalArgumentException("Wrong user ID. Can not find in repository!");
    }


}