package com.moviesappbackend.demo.repository;

import com.moviesappbackend.demo.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    List<Movie> findByTitleContainingIgnoreCase(String title);
}
