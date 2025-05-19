package com.moviesappbackend.demo.auth;

import com.moviesappbackend.demo.config.JwtService;
import com.moviesappbackend.demo.entities.User;
import com.moviesappbackend.demo.entities.UserCredentials;
import com.moviesappbackend.demo.enums.UserRole;
import com.moviesappbackend.demo.repository.UserCredentialsRepository;
import com.moviesappbackend.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, UserCredentialsRepository userCredentialsRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userCredentialsRepository = userCredentialsRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        //if i reach this point, the user is authenticated
        var userCredentials = userCredentialsRepository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(userCredentials);
        System.out.println("\n GENERATED TOKEN: "+jwtToken+"\n");

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse registerUser(UserRegisterRequest request) {

        String username = request.getFirstName() + System.currentTimeMillis()%10000;
        String password = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail(), UserRole.ROLE_REGULAR);

        UserCredentials userCredentials = new UserCredentials(username, password, UserRole.ROLE_REGULAR);

        UserCredentials savedCredentials = userCredentialsRepository.save(userCredentials);

        user.setUserCredentials(savedCredentials);
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(savedCredentials);

        return new AuthenticationResponse(jwtToken);
    }
}
