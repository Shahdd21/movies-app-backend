package com.moviesappbackend.demo.controllers;

import com.moviesappbackend.demo.dto.UserDTO;
import com.moviesappbackend.demo.entities.UserCredentials;
import com.moviesappbackend.demo.enums.UserRole;
import com.moviesappbackend.demo.service.UserService;
import com.moviesappbackend.demo.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userServiceImp;

    @Autowired
    public UserController(UserService userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/profile")
    public UserDTO getUserProfile(@AuthenticationPrincipal UserCredentials userCredentials) throws RuntimeException {

        if(userCredentials.getRole() != UserRole.ROLE_REGULAR){
            throw new RuntimeException("This endpoint is for users only");
        }

        return userServiceImp.getUserProfile(userCredentials);
    }
}
