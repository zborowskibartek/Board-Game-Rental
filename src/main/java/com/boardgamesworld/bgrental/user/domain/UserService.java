package com.boardgamesworld.bgrental.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UserService {

    private UserRepository userRepository;
    private UserValidator userValidator;

    @Autowired
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
        userValidator.validateUpdatedUser(updatedUser);
        userRepository.updateUser(userId, updatedUser);
    }

    void deleteUser(long userId) {
        userRepository.deleteUser(userId);
    }

}