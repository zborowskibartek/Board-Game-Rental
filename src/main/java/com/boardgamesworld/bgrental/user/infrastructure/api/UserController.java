package com.boardgamesworld.bgrental.user.infrastructure.api;

import com.boardgamesworld.bgrental.user.domain.InvalidUserException;
import com.boardgamesworld.bgrental.user.domain.User;
import com.boardgamesworld.bgrental.user.domain.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getAllUser() {
        List<User> users = userFacade.getAllUser();
        List<UserDto> usersDto = users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new UserResponse(usersDto));
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDto userDto) {
        User user = fromDto(userDto);
        try {
            userFacade.addUser(user);
        } catch (InvalidUserException exception) {
            return ResponseEntity.badRequest().body(exception.getUserExceptions());
        }
        URI uri = URI.create("/users/" + user.getUserId());
        logger.info("Add new user! " + user.getUserId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable long userId) {
        User user = userFacade.getUser(userId);
        if (user != null) {
            UserDto userDto = toDto(user);
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
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        User updatedUser = fromDto(updatedUserDto);
        try {
            userFacade.updateUser(userId, updatedUser);
        } catch (InvalidUserException exception) {
            return ResponseEntity.badRequest().body(exception.getUserExceptions());
        }
        logger.info("Updated user " + userId + "!");
        return ResponseEntity.ok().build();
    }

    private UserDto toDto(User user) {
        return new UserDto(user.getUserId(),
                user.getFirstName(),
                user.getSecondName(),
                user.getEmail(),
                user.getNick(),
                user.getPassword());
    }

    private User fromDto(UserDto userDto) {
        return new User(userDto.getUserId(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getEmail(),
                userDto.getNick(),
                userDto.getPassword());
    }
}
