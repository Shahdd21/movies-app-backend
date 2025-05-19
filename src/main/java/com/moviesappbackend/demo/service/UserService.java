package com.moviesappbackend.demo.service;

import com.moviesappbackend.demo.dto.UserDTO;
import com.moviesappbackend.demo.entities.UserCredentials;

public interface UserService {

    UserDTO getUserProfile(UserCredentials userCredentials);
}
