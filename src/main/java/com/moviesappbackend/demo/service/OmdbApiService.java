package com.moviesappbackend.demo.service;

import com.moviesappbackend.demo.dto.QueryParams;
import com.moviesappbackend.demo.entities.Movie;
import com.moviesappbackend.demo.entities.FullDetailsMovie;

import java.util.List;

public interface OmdbApiService {

    FullDetailsMovie getMovieById(String imdbId);

    Movie getMovie(QueryParams queryParams);

    List<Movie> search(QueryParams queryParams);
}
