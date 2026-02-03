package com.movieticket.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieticket.dto.CreateBookingRequest;
import com.movieticket.model.*;
import com.movieticket.repo.BookingRepository;
import com.movieticket.repo.ShowRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final SeatLockManager seatLockManager;

    public BookingService(BookingRepository bookingRepository, ShowRepository showRepository, SeatLockManager seatLockManager) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.seatLockManager = seatLockManager;
    }

    public List<String> occupiedSeats(Long showId) {
        Show show = showRepository.findById(showId).orElseThrow();
        return bookingRepository.findByShow(show).stream()
                .filter(b -> b.getStatus() == BookingStatus.CONFIRMED)
                .flatMap(b -> Arrays.stream(b.getSeatsCsv().split(",")))
                .filter(s -> !s.isBlank())
                .map(String::trim)
                .distinct()
                .toList();
    }

    @Transactional
    public Booking lockSeats(CreateBookingRequest req) {
        Show show = showRepository.findById(req.getShowId()).orElseThrow();
        // check seats not already confirmed
        List<String> occupied = occupiedSeats(show.getId());
        for (String seat : req.getSeats()) {
            if (occupied.contains(seat)) {
                throw new IllegalStateException("Seat already booked: " + seat);
            }
        }
        boolean locked = seatLockManager.tryLock(show.getId(), req.getSeats());
        if (!locked) {
            throw new IllegalStateException("Selected seats are temporarily locked by someone else");
        }
        Booking provisional = new Booking();
        provisional.setShow(show);
        provisional.setUserEmail(req.getUserEmail());
        provisional.setStatus(BookingStatus.PENDING);
        provisional.setSeatsCsv(String.join(",", req.getSeats()));
        provisional.setTotalAmount(req.getSeats().size() * show.getBasePrice());
        provisional.setCreatedAt(LocalDateTime.now());
        return bookingRepository.save(provisional);
    }

    @Transactional
    public Booking confirm(Long provisionalId) {
        Booking b = bookingRepository.findById(provisionalId).orElseThrow();
        if (b.getStatus() == BookingStatus.CONFIRMED) return b;
        b.setStatus(BookingStatus.CONFIRMED);
        // release locks as they are now booked
        List<String> seats = Arrays.stream(b.getSeatsCsv().split(",")).map(String::trim).toList();
        seatLockManager.release(b.getShow().getId(), seats);
        return bookingRepository.save(b);
    }


    public Booking get(Long id) {
        return bookingRepository.findById(id).orElseThrow();
    }
}