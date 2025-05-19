package com.moviesappbackend.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @Id
    @Column(name = "imdb_id")
    @JsonProperty("imdbID")
    private String imdbId;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Poster")
    private String poster;

    Movie(){

    }

    public Movie(String imdbId, String title, String year, String type, String poster) {
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.type = type;
        this.poster = poster;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdbId='" + imdbId + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
