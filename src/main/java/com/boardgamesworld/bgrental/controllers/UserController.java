package com.boardgamesworld.bgrental.controllers;

import com.boardgamesworld.bgrental.model.User;
import com.boardgamesworld.bgrental.model.UserDto;
import com.boardgamesworld.bgrental.services.UserService;
import com.boardgamesworld.bgrental.services.exceptions.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
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

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDto userDto) {
        try {
            User user = new User(userDto.getUserId(),
                    userDto.getFirstName(),
                    userDto.getSecondName(),
                    userDto.getEmail(),
                    userDto.getNick(),
                    userDto.getPassword());

            userService.addUser(user);

            URI uri = URI.create("/users/" + user.getUserId());

            return ResponseEntity.created(uri).build();
        } catch (InvalidUserException exception) {
            return ResponseEntity.badRequest().body(exception.getUserExceptions());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable long userId) {
        User user = userService.getUser(userId);

        if (user != null) {
            UserDto userDto = new UserDto(user.getUserId(),
                    user.getFirstName(),
                    user.getSecondName(),
                    user.getEmail(),
                    user.getNick(),
                    user.getPassword());

            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable long userId, @RequestBody UserDto updatedUserDto) {
        User user = userService.getUser(userId);

        if (user != null) {
            try {
                User updatedUser = new User(
                        userId,
                        updatedUserDto.getFirstName(),
                        updatedUserDto.getSecondName(),
                        updatedUserDto.getEmail(),
                        updatedUserDto.getNick(),
                        updatedUserDto.getPassword());

                userService.updateUser(userId, updatedUser);

                URI uri = URI.create("/users/" + userId);

                return ResponseEntity.created(uri).build();
            } catch (InvalidUserException exception) {
                return ResponseEntity.badRequest().body(exception.getUserExceptions());
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
