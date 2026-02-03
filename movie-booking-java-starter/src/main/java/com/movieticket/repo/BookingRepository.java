package com.movieticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticket.model.Booking;
import com.movieticket.model.Show;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByShow(Show show);
}