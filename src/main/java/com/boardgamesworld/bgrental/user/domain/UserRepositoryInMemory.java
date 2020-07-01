package com.boardgamesworld.bgrental.user.domain;

import java.util.*;

class UserRepositoryInMemory implements UserRepository {

    private static UserRepositoryInMemory INSTANCE;

    private final Map<Long, User> users = new HashMap<>();

    private UserRepositoryInMemory() {
    }

    static UserRepositoryInMemory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepositoryInMemory();
        }
        return INSTANCE;
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
    public void updateUser(long userId, User updatedUser) {
        users.put(userId, updatedUser);
    }

    @Override
    public void deleteUser(long userId) {
        users.remove(userId);
    }

}