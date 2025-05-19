package com.moviesappbackend.demo.controllers;

import com.moviesappbackend.demo.entities.FullDetailsMovie;
import com.moviesappbackend.demo.entities.Movie;
import com.moviesappbackend.demo.service.MovieService;
import com.moviesappbackend.demo.service.OmdbApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final OmdbApiService omdbApiService;

    @Autowired
    public MovieController(MovieService movieService, OmdbApiService omdbApiService) {
        this.movieService = movieService;
        this.omdbApiService = omdbApiService;
    }

    @GetMapping
    public List<Movie> getAllMovies(){

        return movieService.loadFromDatabase();
    }

    @GetMapping("/{imdbId}")
    public FullDetailsMovie getMovieDetails(@PathVariable String imdbId){

        FullDetailsMovie fullDetailsMovie = null;

        try {
            fullDetailsMovie = omdbApiService.getMovieById(imdbId);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return fullDetailsMovie;
    }

    @PostMapping("/search")
    public List<Movie> searchForMovies(@RequestParam String keyword){

        return movieService.getMoviesByKeyword(keyword);
    }

    @PostMapping("/{imdbId}")
    public String rateMovie(@PathVariable String imdbId, @RequestBody Map<String, Integer> map){

        String movieName = movieService.getMovieById(imdbId).getTitle();

        return "You rated " + movieName +" with " +
                map.get("rating") + " stars !";
    }
}
