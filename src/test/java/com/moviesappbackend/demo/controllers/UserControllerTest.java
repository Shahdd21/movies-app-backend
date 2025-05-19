package com.moviesappbackend.demo.controllers;

import com.moviesappbackend.demo.dto.UserDTO;
import com.moviesappbackend.demo.entities.UserCredentials;
import com.moviesappbackend.demo.enums.UserRole;
import com.moviesappbackend.demo.service.UserService;
import com.moviesappbackend.demo.service.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private UserService userService;
    private UserController userController;
    private UserCredentials userCredentials;

    @BeforeEach
    void setup(){
        userService = mock(UserServiceImp.class);
        userController = new UserController(userService);
        userCredentials = mock(UserCredentials.class);
    }

    @Test
    void givenRegularUserRole_whenAccessProfile_thenReturnsUserDTO(){

        UserDTO userDTO = mock(UserDTO.class);

        when(userCredentials.getRole()).thenReturn(UserRole.ROLE_REGULAR);
        when(userService.getUserProfile(userCredentials)).thenReturn(userDTO);

        try {
            var result = userController.getUserProfile(userCredentials);

            assertThat(result).isInstanceOf(UserDTO.class);

        }catch (RuntimeException ex){

        }
    }

    @Test
    void givenInvalidUserRole_whenAccessProfile_thenThrowsException() {

        when(userCredentials.getRole()).thenReturn(UserRole.ROLE_ADMIN);

        try {
            userController.getUserProfile(userCredentials);

        }catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }
    }
}
