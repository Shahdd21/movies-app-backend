package com.moviesappbackend.demo.controllers;

import com.moviesappbackend.demo.dto.QueryParams;
import com.moviesappbackend.demo.entities.Movie;
import com.moviesappbackend.demo.service.MovieService;
import com.moviesappbackend.demo.service.OmdbApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final OmdbApiService omdbApiServiceImp;
    private final MovieService movieServiceImp;

    @Autowired
    public AdminController(OmdbApiService omdbApiServiceImp, MovieService movieServiceImp) {
        this.omdbApiServiceImp = omdbApiServiceImp;
        this.movieServiceImp = movieServiceImp;
    }

    @PostMapping("/movie")
    public Movie getSpecificMovie(@RequestBody QueryParams queryParams){

        Movie result = null;
        try {
            result =  omdbApiServiceImp.getMovie(queryParams);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @PostMapping("/search")
    public List<Movie> SearchForMovie(@RequestBody QueryParams queryParams){

        List<Movie> result = null;

        try {
            result = omdbApiServiceImp.search(queryParams);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @GetMapping("/load")
    public List<Movie> loadFromDatabase(){

        return movieServiceImp.loadFromDatabase();
    }

    @PostMapping("/add/{imdbId}")
    public String addMovieToDB(@PathVariable String imdbId){

        QueryParams queryParams = new QueryParams();
        queryParams.setImdbId(imdbId);

        Movie movie = omdbApiServiceImp.getMovie(queryParams);

        if(movie == null) return "No such movie with this id - "+ imdbId;

        movieServiceImp.addToDB(movie);

        return movie.getTitle() + " is added successfully to the database !";
    }

    @DeleteMapping("/remove/{imdbId}")
    public String removeMovieFromDB(@PathVariable String imdbId){

        boolean success = movieServiceImp.removeMovie(imdbId);

        if(success){
            return "movie with id - " + imdbId+" is deleted successfully";
        }

        return "no such movie with id - " + imdbId;
    }
}
