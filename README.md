# Description
A movie application that used OMDB API to fetch movies and saves from it in the database.

# Features
### Regular Users
- Registers and Log in
- View availabe movies in the application
- Search for movies
- Rate a movie
  
### Admins
- Get a specific movie from the external API
- Search for movies from the API
- Add movie fetched to the database
- Remove movie from the database


# Endpoints

## admins only (/admin)
- /movie : takes parameters in the request body and gets a specifc movie from the api
- /search: takes parameters in the request body and gets a list of movies matching the parameters from the api
- /load : fetches movies from the database
- /add/imdbId : fetches the movie from the api and adds it to the database
- /remove/imdbId: deletes movie from database

## Regular users only (/user)
- /profile : gets user information

## Authentication - For all (/auth)
- /register
- /login

## Movies Navigation - For all (/movies)
- /movies : gets all movies
- /imdbId: gets movie details
- /search : gets all movies matching the parameters passed in the request body
- /imdbId: rate movie

# Tech Stack
- Backend: Java, Spring Boot, Spring Security, JWT
- Database: MySQL

# Testing
- unit tests using Junit and Mockito
- API testing using Postman
