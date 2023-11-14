package io.chechi.mymovies.service;


import io.chechi.mymovies.entity.Movie;

public interface OmdbService {

    Movie searchMovie (String title);

}
