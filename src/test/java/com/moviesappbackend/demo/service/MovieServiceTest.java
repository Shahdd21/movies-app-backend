package com.moviesappbackend.demo.service;

import com.moviesappbackend.demo.entities.Movie;
import com.moviesappbackend.demo.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    private MovieRepository movieRepository;
    private MovieService movieService;

    @BeforeEach
    void setup(){
        movieRepository = mock(MovieRepository.class);
        movieService = new MovieServiceImp(movieRepository);
    }

    @Test
    void givenExistentId_whenGettingMovieById_thenReturnMovie(){

        Movie movie = mock(Movie.class);
        Optional<Movie> optionalMovie = Optional.of(movie);
        when(movieRepository.findById(anyString())).thenReturn(optionalMovie);

        var result = movieService.getMovieById(anyString());

        assertThat(result).isInstanceOf(Movie.class);
    }

    @Test
    void givenNonExistentId_whenGettingMovieById_thenThrowsException(){

        Optional<Movie> optionalMovie = Optional.ofNullable(null);
        when(movieRepository.findById(anyString())).thenReturn(optionalMovie);

        try {
            var result = movieService.getMovieById(anyString());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void givenExistentId_whenDeletingFromDB_thenReturnsTrue(){

        Movie movie = mock(Movie.class);
        Optional<Movie> optionalMovie = Optional.of(movie);
        when(movieRepository.findById(anyString())).thenReturn(optionalMovie);

        boolean result = movieService.removeMovie(anyString());

        assertThat(result).isTrue();
    }

    @Test
    void givenNonExistentId_whenDeletingFromDB_thenReturnsTrue(){

        Optional<Movie> optionalMovie = Optional.empty();
        when(movieRepository.findById(anyString())).thenReturn(optionalMovie);

        boolean result = movieService.removeMovie(anyString());

        assertThat(result).isFalse();
    }
}
