package com.boardgamesworld.bgrental.user.domain;


import java.util.List;

class UserService {

    private static UserService INSTANCE;

    private UserRepository userRepository = UserRepositoryInMemory.getInstance();
    private UserValidator userValidator = UserValidator.getInstance();


    private UserService() {
    }

    static UserService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    List<User> getAllUser() {
        return userRepository.getAllUser();
    }

    User getUser(long userId) {
        return userRepository.getUser(userId);
    }

    void addUser(User user) {
        userValidator.validateNewUser(user);
        userRepository.addUser(user);
    }

    void updateUser(long userId, User updatedUser) {
        userValidator.validateUpdatedUser(updatedUser);
        userRepository.updateUser(userId, updatedUser);
    }

    void deleteUser(long userId) {
        userRepository.deleteUser(userId);
    }

}