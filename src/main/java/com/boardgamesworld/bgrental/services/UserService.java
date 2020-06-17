package com.boardgamesworld.bgrental.services;

import com.boardgamesworld.bgrental.model.User;
import com.boardgamesworld.bgrental.repositories.interfaces.BoardGameRepository;
import com.boardgamesworld.bgrental.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository customerRepository;
    private BoardGameRepository boardGameRepository;

    @Autowired
    public UserService(UserRepository customerRepository, BoardGameRepository boardGameRepository) {
        this.customerRepository = customerRepository;
        this.boardGameRepository = boardGameRepository;
    }

    public void addBoardGameToUserRentedHistory(long userId, long boardGameId){
        customerRepository.getUser(userId)
                .getRentedBoardGamesHistory()
                .add(boardGameRepository.getBoardGame(boardGameId));
    }

    public void addBoardGameToUserAtPresentRentedList(long userId, long boardGameId){
        customerRepository.getUser(userId)
                .getAtPresentRentedBoardGames()
                .add(boardGameRepository.getBoardGame(boardGameId));
    }

    public void removeBoardGameFromUserAtPresentRentedList(long userId, long boardGameId){
        customerRepository.getUser(userId)
                .getAtPresentRentedBoardGames()
                .remove(boardGameRepository.getBoardGame(boardGameId));
    }

    public List<User> getAllUser() {
        return customerRepository.getAllUser();
    }

    public User getUser(long userId) {
        return customerRepository.getUser(userId);
    }

    public void addUser(User user) {
        customerRepository.addUser(user);
    }

    public void deleteUser(long userId) {
        customerRepository.deleteUser(userId);
    }


}
