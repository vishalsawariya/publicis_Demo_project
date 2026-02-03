package com.movieticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticket.model.Movie;
import com.movieticket.model.Screen;
import com.movieticket.model.Show;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByScreenInAndStartTimeBetween(List<Screen> screens, LocalDateTime start, LocalDateTime end);
    List<Show> findByMovie(Movie movie);
}