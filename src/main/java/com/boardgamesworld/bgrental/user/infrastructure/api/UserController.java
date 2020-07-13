package com.boardgamesworld.bgrental.user.infrastructure.api;

import com.boardgamesworld.bgrental.user.domain.InvalidUserException;
import com.boardgamesworld.bgrental.user.domain.User;
import com.boardgamesworld.bgrental.user.domain.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserFacade userFacade;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public List<UserDto> getAllUser() {
        List<User> users = userFacade.getAllUser();
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

            userFacade.addUser(user);
            URI uri = URI.create("/users/" + user.getUserId());
            logger.info("Add new user!");

            return ResponseEntity.created(uri).build();
        } catch (InvalidUserException exception) {
            return ResponseEntity.badRequest().body(exception.getUserExceptions());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable long userId) {
        User user = userFacade.getUser(userId);

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
        userFacade.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable long userId, @RequestBody UserDto updatedUserDto) {
        User user = userFacade.getUser(userId);
        if (user != null) {
            try {
                User updatedUser = new User(
                        userId,
                        updatedUserDto.getFirstName(),
                        updatedUserDto.getSecondName(),
                        updatedUserDto.getEmail(),
                        updatedUserDto.getNick(),
                        updatedUserDto.getPassword());

                userFacade.updateUser(userId, updatedUser);
                URI uri = URI.create("/users/" + userId);
                logger.info("Updated user " + userId + "!");


                return ResponseEntity.created(uri).build();
            } catch (InvalidUserException exception) {
                return ResponseEntity.badRequest().body(exception.getUserExceptions());
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
