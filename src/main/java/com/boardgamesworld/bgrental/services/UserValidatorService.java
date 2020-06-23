package com.boardgamesworld.bgrental.services;

import com.boardgamesworld.bgrental.model.User;
import com.boardgamesworld.bgrental.repositories.UserRepository;
import com.boardgamesworld.bgrental.services.exceptions.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserValidatorService {

    @Autowired
    private UserRepository userRepository;

    public UserValidatorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserValid(User user) {
        List<String> errors = new ArrayList<>();
        if (!isFirstNameValid(user.getFirstName())) {
            errors.add("Invalid user first name!");
        }
        if (!isSecondNameValid(user.getSecondName())) {
            errors.add("Invalid user second name!");
        }
        if (!isEmailValid(user.getEmail())) {
            errors.add("Invalid user email!");
        }
        if (!isNickValid(user.getNick())) {
            errors.add("Invalid user nick!");
        }
        if (isEmailAlreadyInUse(user.getEmail())) {
            errors.add("Email is already in use!");
        }
        if (isNickAlreadyInUse(user.getNick())) {
            errors.add("Nick is already in use!");
        }
        if (errors.size() > 0) {
            throw new InvalidUserException(errors);
        }
        return true;
    }

    private boolean isFirstNameValid(String firstName) {
        return Pattern.matches("[A-Z][a-z]{1,14}",firstName);
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

    private boolean isEmailAlreadyInUse(String email){
        return userRepository.getAllUser().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    private boolean isNickAlreadyInUse(String nick){
        return userRepository.getAllUser().stream()
                .anyMatch(user -> user.getNick().equals(nick));
    }


}
