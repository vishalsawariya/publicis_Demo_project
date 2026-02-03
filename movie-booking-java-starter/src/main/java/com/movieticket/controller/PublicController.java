package com.movieticket.controller;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.movieticket.model.Movie;
import com.movieticket.model.Show;
import com.movieticket.service.DiscoveryService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    private final DiscoveryService discoveryService;

    public PublicController(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @GetMapping("/cities")
    public Set<String> cities() {
        return discoveryService.listCities();
    }

    @GetMapping("/movies")
    public List<Movie> movies(@RequestParam @NotBlank String city) {
        return discoveryService.listMoviesByCity(city);
    }

    @GetMapping("/shows")
    public List<Show> shows(@RequestParam @NotBlank String city,
                            @RequestParam Long movieId,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return discoveryService.listShows(city, movieId, date);
    }
}