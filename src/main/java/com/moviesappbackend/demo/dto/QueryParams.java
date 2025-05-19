package com.moviesappbackend.demo.dto;

public class QueryParams {

    private String imdbId;
    private String title;
    private String type;
    private String year;

    public QueryParams(){

    }

    public QueryParams(String imdbId, String title, String type, String year) {
        this.imdbId = imdbId;
        this.title = title;
        this.type = type;
        this.year = year;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
