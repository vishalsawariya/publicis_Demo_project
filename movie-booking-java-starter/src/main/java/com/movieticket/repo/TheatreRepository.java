package com.movieticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticket.model.Theatre;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    List<Theatre> findByCityIgnoreCase(String city);
}