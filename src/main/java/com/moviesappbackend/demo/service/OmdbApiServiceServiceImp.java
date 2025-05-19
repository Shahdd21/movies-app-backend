package com.moviesappbackend.demo.service;

import com.moviesappbackend.demo.dto.OmdbResponse;
import com.moviesappbackend.demo.dto.QueryParams;
import com.moviesappbackend.demo.entities.Movie;
import com.moviesappbackend.demo.entities.FullDetailsMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OmdbApiServiceServiceImp implements OmdbApiService {

    private final String URL = "http://www.omdbapi.com/?apikey=9c267b12";

    private final RestTemplate restTemplate;

    @Autowired
    public OmdbApiServiceServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FullDetailsMovie getMovieById(String imdbId){

        FullDetailsMovie fullDetailsMovie = restTemplate.getForObject(URL+"&i="+imdbId,
                FullDetailsMovie.class);

        if (fullDetailsMovie == null) throw new IllegalArgumentException("No such movie with this id - "+ imdbId);

        return fullDetailsMovie;
    }

    public Movie getMovie(QueryParams queryParams){

        StringBuilder endpoint = new StringBuilder(URL);

        if(queryParams.getImdbId() == null && queryParams.getTitle() ==  null)
            throw new RuntimeException("No id is entered");

        if(queryParams.getImdbId() != null) {
            endpoint.append("&").append("i=").append(queryParams.getImdbId());
        }

        if(queryParams.getTitle() != null) {
            endpoint.append("&").append("t=").append(queryParams.getTitle());
        }

        if(queryParams.getType() != null){
            endpoint.append("&").append("type=").append(queryParams.getType());
        }

        if(queryParams.getYear() != null){
            endpoint.append("&").append("y=").append(queryParams.getYear());
        }

        return restTemplate.getForObject(endpoint.toString(), Movie.class);
    }

    public List<Movie> search(QueryParams queryParams){

        StringBuilder endpoint = new StringBuilder(URL);

        if(queryParams.getTitle() == null) throw new RuntimeException("No keyword is entered for search");

        endpoint.append("&").append("s=").append(queryParams.getTitle());

        if(queryParams.getType() != null){
            endpoint.append("&").append("type=").append(queryParams.getType());
        }

        if(queryParams.getYear() != null){
            endpoint.append("&").append("y=").append(queryParams.getYear());
        }

        OmdbResponse omdbResponse = restTemplate.getForObject(endpoint.toString(), OmdbResponse.class);

        return omdbResponse.getSearch();
    }
}
