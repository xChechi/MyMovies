package io.chechi.mymovies.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.chechi.mymovies.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class OmdbResponseConverter {

    private final ObjectMapper objectMapper;

    public OmdbResponseConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Movie convertToMovie(String apiResponse) {
        try {
            JsonNode jsonNode = objectMapper.readTree(apiResponse);

            Movie movie = new Movie();
            movie.setTitle(jsonNode.get("Title").asText());
            movie.setYear(jsonNode.get("Year").asText());
            movie.setImdbId(jsonNode.get("imdbID").asText());

            // Set other fields as needed...

            return movie;
        } catch (Exception e) {
            // Handle exception (e.g., log it) and return null or throw a custom exception
            e.printStackTrace();
            return null;
        }
    }
}
