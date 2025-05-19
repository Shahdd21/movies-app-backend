package com.moviesappbackend.demo.service;

import com.moviesappbackend.demo.entities.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> loadFromDatabase();

    Movie getMovieById(String imdbId);

    void addToDB(Movie movie);

    boolean removeMovie(String imdbId);

    List<Movie> getMoviesByKeyword(String keyword);

}
