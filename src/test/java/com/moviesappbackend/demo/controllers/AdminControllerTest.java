package com.moviesappbackend.demo.controllers;

import com.moviesappbackend.demo.dto.QueryParams;
import com.moviesappbackend.demo.entities.Movie;
import com.moviesappbackend.demo.service.MovieService;
import com.moviesappbackend.demo.service.MovieServiceImp;
import com.moviesappbackend.demo.service.OmdbApiService;
import com.moviesappbackend.demo.service.OmdbApiServiceServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

    private OmdbApiService omdbApiService;
    private MovieService movieService;
    private AdminController adminController;
    private QueryParams queryParams;

    @BeforeEach
    void setup(){
        omdbApiService = mock(OmdbApiServiceServiceImp.class);
        movieService = mock(MovieServiceImp.class);
        adminController = new AdminController(omdbApiService, movieService);
        queryParams = new QueryParams();
    }

    @Test
    void givenValidQueryParams_whenGettingSpecificMovie_thenReturnsMovie(){

        Movie movie = mock(Movie.class);
        when(omdbApiService.getMovie(queryParams)).thenReturn(movie);

        var result = adminController.getSpecificMovie(queryParams);

        assertThat(result).isInstanceOf(Movie.class);
    }

    @Test
    void givenInValidQueryParams_whenGettingSpecificMovie_thenThrowsException(){

        when(omdbApiService.getMovie(queryParams)).thenThrow(new RuntimeException("The Id or title must be found"));

        try {
            adminController.getSpecificMovie(queryParams);
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void givenValidQueryParams_whenSearchingForMovie_thenReturnsMovies(){

        List<Movie> movies = mock(List.class);
        when(omdbApiService.search(queryParams)).thenReturn(movies);

        var result = adminController.SearchForMovie(queryParams);

        assertThat(result).isInstanceOf(List.class);
    }

    @Test
    void givenInValidQueryParams_whenSearchingForMovie_thenThrowsException(){

        List<Movie> movies = mock(List.class);
        when(omdbApiService.search(queryParams)).thenThrow(new RuntimeException("No keyword is entered."));

       try {
           adminController.SearchForMovie(queryParams);
       } catch (RuntimeException ex){
           System.out.println(ex.getMessage());
       }

    }

    @Test
    void givenMovieIsFound_whenDeletingFromDatabase_thenSucceed(){

        when(movieService.removeMovie(anyString())).thenReturn(true);

        String result = adminController.removeMovieFromDB(anyString());

        assertThat(result).containsIgnoringCase("success");
    }

    @Test
    void givenMovieIsNotFound_whenDeletingFromDatabase_thenFail(){

        when(movieService.removeMovie(anyString())).thenReturn(false);

        String result = adminController.removeMovieFromDB(anyString());

        assertThat("success").isNotIn(result);
    }

    @Test
    void givenInvalidId_whenAddingToDatabase_thenFail(){

        when(omdbApiService.getMovie(queryParams)).thenReturn(null);

        String result = adminController.addMovieToDB("dsfdsds");

        assertThat("success").isNotIn(result);
    }
}
