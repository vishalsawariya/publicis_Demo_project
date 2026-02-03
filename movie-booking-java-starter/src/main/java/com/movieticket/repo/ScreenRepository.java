package com.movieticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticket.model.Screen;
import com.movieticket.model.Theatre;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
    List<Screen> findByTheatre(Theatre theatre);
}