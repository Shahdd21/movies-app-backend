package com.moviesappbackend.demo.repository;

import com.moviesappbackend.demo.entities.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer> {

    Optional<UserCredentials> findByUsername(String username);
}
