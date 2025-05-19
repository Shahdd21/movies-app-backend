package com.moviesappbackend.demo.service;

import com.moviesappbackend.demo.entities.Movie;
import com.moviesappbackend.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MovieServiceImp implements MovieService{

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImp(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> loadFromDatabase() {

        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(String imdbId) {

        Optional<Movie> movie = movieRepository.findById(imdbId);

        if(movie.isPresent()) return movie.get();

        throw new NoSuchElementException("No such element with id - "+imdbId);
    }

    public void addToDB(Movie movie) {

        movieRepository.save(movie);
    }

    public boolean removeMovie(String imdbId) {

        Optional<Movie> movie = movieRepository.findById(imdbId);

        if(movie.isPresent()){
            movieRepository.delete(movie.get());
            return true;
        }

        return false;
    }

    @Override
    public List<Movie> getMoviesByKeyword(String keyword) {

         return movieRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
