package com.boardgamesworld.bgrental.user;

import com.boardgamesworld.bgrental.user.domain.UserFacade;
import com.boardgamesworld.bgrental.user.dto.UserDto;
import com.boardgamesworld.bgrental.user.exceptions.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    private UserFacade userFacade;

    @GetMapping
    public List<UserDto> getAllUser() {
        return userFacade.getAllUser();
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDto userDto) {
        try {
            userFacade.addUser(userDto);
            URI uri = URI.create("/users/" + userDto.getUserId());

            return ResponseEntity.created(uri).build();
        } catch (InvalidUserException exception) {
            return ResponseEntity.badRequest().body(exception.getUserExceptions());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable long userId) {
        UserDto userDto = userFacade.getUser(userId);
        if (userDto != null) {
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
        UserDto userDto = userFacade.getUser(userId);
        if (userDto != null) {
            try {
                userFacade.updateUser(userId, updatedUserDto);
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
