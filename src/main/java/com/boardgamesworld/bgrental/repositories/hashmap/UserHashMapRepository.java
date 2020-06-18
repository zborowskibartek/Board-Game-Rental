package com.boardgamesworld.bgrental.repositories.hashmap;

import com.boardgamesworld.bgrental.model.*;
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
        createInitialUsers(users);
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
    public void updateUser(long userIdToUpdate, User userWithUpdatedProperties) {
        if (isUserInRepository(userIdToUpdate)) {
            updateUserProperties(getUser(userIdToUpdate), userWithUpdatedProperties);
        }
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

    private void updateUserProperties(User userToUpdate, User userWithUpdatedProperties) {
        if (userWithUpdatedProperties.getFirstName() != null) {
            userToUpdate.setFirstName(userWithUpdatedProperties.getFirstName());
        }
        if (userWithUpdatedProperties.getSecondName() != null) {
            userToUpdate.setSecondName(userWithUpdatedProperties.getSecondName());
        }
        if (userWithUpdatedProperties.getEmail() != null) {
            userToUpdate.setEmail(userWithUpdatedProperties.getEmail());
        }
    }

    private static void createInitialUsers(Map users) {
        User user = new User(1, "Bartek", "Zboro",
                "email@example.com", "bartas95", "password123", new HashSet<>(), new ArrayList<>());
        users.put(user.getUserId(), user);
    }

}