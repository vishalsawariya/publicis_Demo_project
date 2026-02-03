package com.movieticket.dto;

import jakarta.validation.constraints.NotNull;

public class ConfirmBookingRequest {
    @NotNull
    private Long showId;
    @NotNull
    private Long provisionalId; // id returned by lock step

    public Long getShowId() { return showId; }
    public void setShowId(Long showId) { this.showId = showId; }
    public Long getProvisionalId() { return provisionalId; }
    public void setProvisionalId(Long provisionalId) { this.provisionalId = provisionalId; }
}