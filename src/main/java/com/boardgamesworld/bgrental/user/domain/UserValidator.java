package com.boardgamesworld.bgrental.user.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class UserValidator {

    private UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void validateNewUser(User newUser) {
        List<String> errors = new ArrayList<>();
        if (!isFirstNameValid(newUser.getFirstName())) {
            errors.add("Invalid user first name!");
        }
        if (!isSecondNameValid(newUser.getSecondName())) {
            errors.add("Invalid user second name!");
        }
        if (!isEmailValid(newUser.getEmail())) {
            errors.add("Invalid user email!");
        }
        if (!isNickValid(newUser.getNick())) {
            errors.add("Invalid user nick!");
        }
        if (isEmailAlreadyInUse(newUser.getEmail())) {
            errors.add("Email is already in use!");
        }
        if (isNickAlreadyInUse(newUser.getNick())) {
            errors.add("Nick is already in use!");
        }
        if (errors.size() > 0) {
            throw new InvalidUserException(errors);
        }
    }

    void validateUpdatedUser(User updatedUser) {
        List<String> errors = new ArrayList<>();
        if (!isFirstNameValid(updatedUser.getFirstName())) {
            errors.add("Invalid user first name!");
        }
        if (!isSecondNameValid(updatedUser.getSecondName())) {
            errors.add("Invalid user second name!");
        }
        if (isEmailChanged(updatedUser.getUserId(), updatedUser.getEmail())) {
            errors.add("Email can not be changed!");
        }
        if (isNickChanged(updatedUser.getUserId(), updatedUser.getNick())) {
            errors.add("Nick can not be changed!");
        }
        if (errors.size() > 0) {
            throw new InvalidUserException(errors);
        }
    }

    private boolean isFirstNameValid(String firstName) {
        return Pattern.matches("[A-Z][a-z]{1,14}", firstName);
    }

    private boolean isSecondNameValid(String secondName) {
        return Pattern.matches("[A-Z][a-z]{1,14}", secondName);
    }

    private boolean isEmailValid(String email) {
        return Pattern.matches(".+@\\w+(-\\w+)?\\.\\w+", email);
    }

    private boolean isNickValid(String nick) {
        return Pattern.matches("[a-zA-Z0-9]{6,20}", nick);
    }

    private boolean isEmailAlreadyInUse(String email) {
        return userRepository.getAllUser().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    private boolean isNickAlreadyInUse(String nick) {
        return userRepository.getAllUser().stream()
                .anyMatch(user -> user.getNick().equals(nick));
    }

    private boolean isEmailChanged(long userId, String email) {
        User user = userRepository.getUser(userId);

        return !user.getEmail().equals(email);
    }

    private boolean isNickChanged(long userId, String nick) {
        User user = userRepository.getUser(userId);

        return !user.getNick().equals(nick);
    }

}