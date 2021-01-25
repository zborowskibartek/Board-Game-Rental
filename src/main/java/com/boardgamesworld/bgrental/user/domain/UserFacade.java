package com.boardgamesworld.bgrental.user.domain;



import java.util.List;

public class UserFacade {

    private final UserService userService;

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
