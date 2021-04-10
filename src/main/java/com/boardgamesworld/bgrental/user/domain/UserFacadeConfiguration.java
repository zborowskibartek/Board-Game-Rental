package com.boardgamesworld.bgrental.user.domain;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFacadeConfiguration {

    @Bean
    UserFacade userFacade(UserRepository userRepository) {
        UserValidator userValidator = new UserValidator(userRepository);
        UserService userService = new UserService(userRepository, userValidator);
        return new UserFacade(userService);
    }

}
