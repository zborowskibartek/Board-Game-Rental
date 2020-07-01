package com.boardgamesworld.bgrental.services;

import com.boardgamesworld.bgrental.model.User;
import com.boardgamesworld.bgrental.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public List<User> getAllUser() {
        return userRepository.getAllUser();
    }

    public User getUser(long userId) {
        return userRepository.getUser(userId);
    }

    public void addUser(User user) {
        userValidator.validateNewUser(user);
        userRepository.addUser(user);
    }

    public void updateUser(long userId, User updatedUser) {
        userValidator.validateUpdatedUser(updatedUser);
        userRepository.updateUser(userId, updatedUser);
    }

    public void deleteUser(long userId) {
        userRepository.deleteUser(userId);
    }

}