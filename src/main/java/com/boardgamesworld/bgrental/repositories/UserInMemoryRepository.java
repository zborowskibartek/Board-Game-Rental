package com.boardgamesworld.bgrental.repositories;

import com.boardgamesworld.bgrental.model.*;
import com.boardgamesworld.bgrental.repositories.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;


@Primary
@Repository
public class UserInMemoryRepository implements UserRepository {

    private Map<Long, User> users;

    public UserInMemoryRepository() {
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
                "email@example.com", "bartas95", "password123");
        users.put(user.getUserId(), user);
    }

}