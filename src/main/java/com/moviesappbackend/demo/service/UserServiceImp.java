package com.moviesappbackend.demo.service;

import com.moviesappbackend.demo.dto.UserDTO;
import com.moviesappbackend.demo.entities.User;
import com.moviesappbackend.demo.entities.UserCredentials;
import com.moviesappbackend.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserProfile(UserCredentials userCredentials) {

        User user = userCredentials.getUser();

        return new UserDTO(user);
    }
}
