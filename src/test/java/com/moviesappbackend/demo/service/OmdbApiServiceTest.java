package com.moviesappbackend.demo.service;

import com.moviesappbackend.demo.dto.OmdbResponse;
import com.moviesappbackend.demo.dto.QueryParams;
import com.moviesappbackend.demo.entities.FullDetailsMovie;
import com.moviesappbackend.demo.entities.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OmdbApiServiceTest {

    private RestTemplate restTemplate;
    private OmdbApiService omdbApiService;
    private static final String URL = "http://www.omdbapi.com/?apikey=9c267b12";

    @BeforeEach
    void setup(){
        restTemplate = mock(RestTemplate.class);
        omdbApiService = new OmdbApiServiceServiceImp(restTemplate);
    }

    @Test
    void givenValidId_whenGettingMovieById_thenReturnFullDetailsMovie(){

        FullDetailsMovie movie = new FullDetailsMovie();
        movie.setImdbId("tt1285016");

        String fullUrl = URL + "&i="+ "tt1285016";
        when(restTemplate.getForObject(fullUrl, FullDetailsMovie.class)).thenReturn(movie);

        var result = omdbApiService.getMovieById("tt1285016");

        assertThat(result).isInstanceOf(FullDetailsMovie.class);
    }

    @Test
    void givenInValidId_whenGettingMovieById_thenThrowsException(){

        FullDetailsMovie movie = new FullDetailsMovie();
        movie.setImdbId(null);

        String fullUrl = URL + "&i="+ null;
        when(restTemplate.getForObject(fullUrl, FullDetailsMovie.class)).thenReturn(movie);

        try {
            omdbApiService.getMovieById("tt1285016");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void givenNoIdAndNoTitle_whenGettingMovie_thenThrowException(){
        QueryParams queryParams = new QueryParams();
        queryParams.setImdbId(null);
        queryParams.setTitle(null);

        try{
            omdbApiService.getMovie(queryParams);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void givenIdNotNullAndTitleNull_whenGettingMovie_thenReturnsMovie(){

        Movie movie = mock(Movie.class);
        String id = "tt87654";
        String fullUrl = URL + "&i="+ id;
        when(restTemplate.getForObject(fullUrl, Movie.class)).thenReturn(movie);

        QueryParams queryParams = new QueryParams();
        queryParams.setImdbId(id);
        queryParams.setTitle(null);

        var result = omdbApiService.getMovie(queryParams);

        assertThat(result).isInstanceOf(Movie.class);
    }

    @Test
    void givenIdNullAndTitleNotNull_whenGettingMovie_thenReturnsMovie(){

        Movie movie = mock(Movie.class);
        String title = "title";
        String fullUrl = URL + "&t="+ title;
        when(restTemplate.getForObject(fullUrl, Movie.class)).thenReturn(movie);

        QueryParams queryParams = new QueryParams();
        queryParams.setImdbId(null);
        queryParams.setTitle(title);

        var result = omdbApiService.getMovie(queryParams);

        assertThat(result).isInstanceOf(Movie.class);
    }

    @Test
    void givenTitleNull_whenSearchingForMovie_thenThrowsException(){

        QueryParams queryParams = new QueryParams();
        queryParams.setTitle(null);

        try{
            omdbApiService.search(queryParams);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void givenTitleNotNull_whenSearchingForMovie_thenReturnsMovies(){

        String title = "dksjfh";
        QueryParams queryParams = new QueryParams();
        queryParams.setTitle(title);
        List<Movie> movies = mock(List.class);

        String fullUrl = URL +"&s=" + title;
        OmdbResponse response = mock(OmdbResponse.class);

        when(restTemplate.getForObject(fullUrl, OmdbResponse.class)).thenReturn(response);
        when(response.getSearch()).thenReturn(movies);

        var result = omdbApiService.search(queryParams);

        assertThat(result).isInstanceOf(List.class);
    }
}
