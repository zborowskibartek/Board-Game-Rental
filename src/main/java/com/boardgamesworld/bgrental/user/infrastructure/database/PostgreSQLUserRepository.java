package com.boardgamesworld.bgrental.user.infrastructure.database;

import com.boardgamesworld.bgrental.user.domain.User;
import com.boardgamesworld.bgrental.user.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class PostgreSQLUserRepository implements UserRepository {

    private final UserRepositorySQL userRepositorySQL;

    @Override
    public List<User> getAllUser() {
        return userRepositorySQL.findAll();
    }

    @Override
    public User getUser(long userId) {
        Optional<User> user = userRepositorySQL.findById(userId);
        return user.get();
    }

    @Override
    public void addUser(User user) {
        userRepositorySQL.save(user);
    }

    @Transactional
    @Override
    public void updateUser(long userId, User updatedUser) {
        User user = userRepositorySQL.findById(userId).get();
        updateUserFields(user, updatedUser);
    }

    private void updateUserFields(User user, User updatedUser) {
        user.setFirstName(updatedUser.getFirstName());
        user.setSecondName(updatedUser.getSecondName());
        user.setEmail(updatedUser.getEmail());
        user.setNick(updatedUser.getNick());
        user.setPassword(updatedUser.getPassword());
    }

    @Override
    public void deleteUser(long userId) {
        try {
            userRepositorySQL.deleteById(userId);
        } catch (EmptyResultDataAccessException ignored) {
        }

    }
}
