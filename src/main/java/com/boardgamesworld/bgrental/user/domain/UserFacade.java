package com.boardgamesworld.bgrental.user.domain;

import com.boardgamesworld.bgrental.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserFacade {

    private UserService userService = UserService.getInstance();


    public List<UserDto> getAllUser() {
        List<User> users = userService.getAllUser();
        List<UserDto> usersDto = new ArrayList<>();

        users.forEach(user -> usersDto.add(
                new UserDto(user.getUserId(),
                        user.getFirstName(),
                        user.getSecondName(),
                        user.getEmail(),
                        user.getNick(),
                        user.getPassword())));

        return usersDto;
    }

    public UserDto getUser(long userId) {
        User user = userService.getUser(userId);

        return new UserDto(user.getUserId(),
                user.getFirstName(),
                user.getSecondName(),
                user.getEmail(),
                user.getNick(),
                user.getPassword());
    }

    public void addUser(UserDto userDto) {
        User user = new User(userDto.getUserId(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getEmail(),
                userDto.getNick(),
                userDto.getPassword());

        userService.addUser(user);
    }

    public void updateUser(long userId, UserDto updatedUserDto) {
        User updatedUser = new User(
                userId,
                updatedUserDto.getFirstName(),
                updatedUserDto.getSecondName(),
                updatedUserDto.getEmail(),
                updatedUserDto.getNick(),
                updatedUserDto.getPassword());

        userService.updateUser(userId, updatedUser);
    }

    public void deleteUser(long userId) {
        userService.deleteUser(userId);
    }
}
