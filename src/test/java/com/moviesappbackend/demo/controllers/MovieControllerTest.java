package com.moviesappbackend.demo.controllers;

import com.moviesappbackend.demo.entities.FullDetailsMovie;
import com.moviesappbackend.demo.service.MovieService;
import com.moviesappbackend.demo.service.MovieServiceImp;
import com.moviesappbackend.demo.service.OmdbApiService;
import com.moviesappbackend.demo.service.OmdbApiServiceServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieControllerTest {

    private MovieService movieService;
    private OmdbApiService omdbApiService;
    private MovieController movieController;

    @BeforeEach
    void setup(){
        movieService = mock(MovieServiceImp.class);
        omdbApiService = mock(OmdbApiServiceServiceImp.class);
        movieController = new MovieController(movieService, omdbApiService);
    }

    @Test
    void givenValidId_whenGetMovieDetails_thenReturnsMovie(){

        FullDetailsMovie FullDetailsMovie = new FullDetailsMovie();
        when(omdbApiService.getMovieById(anyString())).thenReturn(FullDetailsMovie);

       var result = movieController.getMovieDetails(anyString());

       assertThat(result).isInstanceOf(FullDetailsMovie.class);
    }

    @Test
    void givenInValidId_whenGetMovieDetails_thenThrowsException(){

        when(omdbApiService.getMovieById(anyString())).thenThrow(new RuntimeException("Invalid ImdbID"));

        try {
            movieController.getMovieDetails(anyString());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
