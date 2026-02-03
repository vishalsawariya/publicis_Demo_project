package com.movieticket.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

import com.movieticket.model.Format;

public class CreateShowRequest {
    @NotNull
    private Long movieId;
    @NotNull
    private Long screenId;
    @NotNull @Future
    private LocalDateTime startTime;
    @NotNull
    private Format format;
    @Min(0)
    private double basePrice;

    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    public Long getScreenId() { return screenId; }
    public void setScreenId(Long screenId) { this.screenId = screenId; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public Format getFormat() { return format; }
    public void setFormat(Format format) { this.format = format; }
    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
}