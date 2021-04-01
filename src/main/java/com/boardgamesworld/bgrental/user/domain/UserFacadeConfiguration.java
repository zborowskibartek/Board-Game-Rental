package com.boardgamesworld.bgrental.user.domain;


import com.boardgamesworld.bgrental.user.infrastructure.database.inmemory.UserRepositoryInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFacadeConfiguration {

    public UserFacade userFacade() {
        return new UserFacadeConfiguration().userFacade(new UserRepositoryInMemory());
    }

    @Bean
    UserFacade userFacade(UserRepository userRepository) {
        UserValidator userValidator = new UserValidator(userRepository);
        UserService userService = new UserService(userRepository, userValidator);
        return new UserFacade(userService);
    }

}
