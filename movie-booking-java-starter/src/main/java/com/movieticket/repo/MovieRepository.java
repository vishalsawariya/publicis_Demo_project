package com.movieticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticket.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> { }