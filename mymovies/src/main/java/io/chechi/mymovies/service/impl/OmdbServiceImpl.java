package io.chechi.mymovies.service.impl;

import io.chechi.mymovies.converter.OmdbResponseConverter;
import io.chechi.mymovies.entity.Movie;
import io.chechi.mymovies.repository.MovieRepository;
import io.chechi.mymovies.service.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OmdbServiceImpl implements OmdbService {

    @Value("${omdb.api.key}")
    private String omdbApiKey;

    private final String omdbBaseUrl = "http://www.omdbapi.com/";

    private final RestTemplate restTemplate;
    private final OmdbResponseConverter omdbResponseConverter;
    private final MovieRepository movieRepository;

    @Autowired
    public OmdbServiceImpl(RestTemplate restTemplate, OmdbResponseConverter omdbResponseConverter, MovieRepository movieRepository) {
        this.restTemplate = restTemplate;
        this.omdbResponseConverter = omdbResponseConverter;
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie searchMovie(String title) {

        String url = omdbBaseUrl + "?apikey=" + omdbApiKey + "&t=" + title;
        String apiResponse = restTemplate.getForObject(url, String.class);

        System.out.println(omdbApiKey);
        System.out.println(url);
        System.out.println(apiResponse);

        Movie movie = omdbResponseConverter.convertToMovie(apiResponse);

        if (movie != null) {
            movieRepository.save(movie);
        }

        return movie;
    }
}
