package com.boardgamesworld.bgrental.user.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserFacade {

    private UserService userService;

    @Autowired
    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    public User getUser(long userId) {
        return userService.getUser(userId);
    }

    public void addUser(User user) {
        userService.addUser(user);
    }

    public void updateUser(long userId, User updatedUser) {
        userService.updateUser(userId, updatedUser);
    }

    public void deleteUser(long userId) {
        userService.deleteUser(userId);
    }
}
