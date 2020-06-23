package com.boardgamesworld.bgrental.services;

import com.boardgamesworld.bgrental.model.User;
import com.boardgamesworld.bgrental.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserValidatorService userValidatorService;

    @Autowired
    public UserService(UserRepository userRepository, UserValidatorService userValidatorService) {
        this.userRepository = userRepository;
        this.userValidatorService = userValidatorService;
    }

    public List<User> getAllUser() {
        return userRepository.getAllUser();
    }

    public User getUser(long userId) {
        return userRepository.getUser(userId);
    }

    public void addUser(User user) {
        userValidatorService.isUserValid(user);
        userRepository.addUser(user);
    }

    public void updateUser(long userIdToUpdate, User userWithUpdatedProperties) {
        userRepository.updateUser(userIdToUpdate, userWithUpdatedProperties);
    }

    public void deleteUser(long userId) {
        userRepository.deleteUser(userId);
    }

}
