package com.boardgamesworld.bgrental.controllers;

import com.boardgamesworld.bgrental.model.User;
import com.boardgamesworld.bgrental.services.UserService;
import com.boardgamesworld.bgrental.services.UserValidatorService;
import com.boardgamesworld.bgrental.services.exceptions.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("")
    public ResponseEntity addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (InvalidUserException errors) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable long userId) {
        User returnUser = userService.getUser(userId);
        if (returnUser != null){
            return new ResponseEntity<>(returnUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable long userId) {
       try{
           userService.deleteUser(userId);
           return new ResponseEntity(HttpStatus.NO_CONTENT);
       } catch (IllegalStateException exception){
           return new ResponseEntity(HttpStatus.NOT_FOUND);
       }
    }

    /*  @PutMapping("/{userId}")
      @ResponseStatus(HttpStatus.OK)
      public void updateUser(@PathVariable long userId, @RequestBody User userWithUpdatedProperties) {
          userService.updateUser(userId, userWithUpdatedProperties);
      }*/
}
