package com.movieticket.service;

import org.springframework.stereotype.Service;

import com.movieticket.model.Movie;
import com.movieticket.model.Screen;
import com.movieticket.model.Show;
import com.movieticket.model.Theatre;
import com.movieticket.repo.MovieRepository;
import com.movieticket.repo.ScreenRepository;
import com.movieticket.repo.ShowRepository;
import com.movieticket.repo.TheatreRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiscoveryService {
    private final TheatreRepository theatreRepository;
    private final ScreenRepository screenRepository;
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;

    public DiscoveryService(TheatreRepository theatreRepository, ScreenRepository screenRepository, ShowRepository showRepository, MovieRepository movieRepository) {
        this.theatreRepository = theatreRepository;
        this.screenRepository = screenRepository;
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
    }

    public Set<String> listCities() {
        return theatreRepository.findAll().stream().map(Theatre::getCity).collect(Collectors.toCollection(TreeSet::new));
    }

    public List<Movie> listMoviesByCity(String city) {
        List<Theatre> theatres = theatreRepository.findByCityIgnoreCase(city);
        List<Screen> screens = theatres.stream().flatMap(t -> screenRepository.findByTheatre(t).stream()).toList();
        List<Show> shows = showRepository.findByScreenInAndStartTimeBetween(screens, LocalDate.now().atStartOfDay(), LocalDate.now().plusDays(30).atStartOfDay());
        Set<Long> movieIds = shows.stream().map(s -> s.getMovie().getId()).collect(Collectors.toSet());
        return movieRepository.findAll().stream().filter(m -> movieIds.contains(m.getId())).toList();
    }

    public List<Show> listShows(String city, Long movieId, LocalDate date) {
        List<Theatre> theatres = theatreRepository.findByCityIgnoreCase(city);
        List<Screen> screens = theatres.stream().flatMap(t -> screenRepository.findByTheatre(t).stream()).toList();
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        return showRepository.findByScreenInAndStartTimeBetween(screens, start, end)
                .stream().filter(s -> s.getMovie().getId().equals(movieId)).toList();
    }
}