package com.boardgamesworld.bgrental.user.domain;


import java.util.List;

class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
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
        userValidator.validateUpdatedUser(userId, updatedUser);
        userRepository.updateUser(userId, updatedUser);
    }

    void deleteUser(long userId) {
        userRepository.deleteUser(userId);
    }

}