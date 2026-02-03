package com.movieticket.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.movieticket.dto.ConfirmBookingRequest;
import com.movieticket.dto.CreateBookingRequest;
import com.movieticket.model.Booking;
import com.movieticket.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/occupied")
    public List<String> occupied(@RequestParam Long showId) {
        return bookingService.occupiedSeats(showId);
    }

    @PostMapping("/lock")
    public Booking lock(@Valid @RequestBody CreateBookingRequest req) {
        return bookingService.lockSeats(req);
    }

    @PostMapping("/confirm")
    public Booking confirm(@Valid @RequestBody ConfirmBookingRequest req) {
        return bookingService.confirm(req.getProvisionalId());
    }

    @GetMapping("/{id}")
    public Booking get(@PathVariable Long id) { return bookingService.get(id); }
}