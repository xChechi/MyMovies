package io.chechi.mymovies.controller;

import io.chechi.mymovies.entity.Movie;
import io.chechi.mymovies.service.OmdbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final OmdbService omdbService;

    @GetMapping("/search")
    public String searchMovie (@RequestParam String title, Model model) {
        Movie movie = omdbService.searchMovie(title);

        System.out.println(movie);

        model.addAttribute("movie", movie);
        return "home";
    }
}
