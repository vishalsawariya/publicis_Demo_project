package com.movieticket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class CreateBookingRequest {
    @NotNull
    private Long showId;
    @Email
    private String userEmail;
    @NotEmpty
    private List<String> seats; // e.g., ["A1","A2"]

    public Long getShowId() { return showId; }
    public void setShowId(Long showId) { this.showId = showId; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public List<String> getSeats() { return seats; }
    public void setSeats(List<String> seats) { this.seats = seats; }
}