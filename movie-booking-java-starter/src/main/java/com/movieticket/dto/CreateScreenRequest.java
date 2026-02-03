package com.movieticket.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateScreenRequest {
    @NotNull
    private Long theatreId;
    @NotBlank
    private String name;
    @Min(1)
    private int rowsCount;
    @Min(1)
    private int colsCount;

    public Long getTheatreId() { return theatreId; }
    public void setTheatreId(Long theatreId) { this.theatreId = theatreId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getRowsCount() { return rowsCount; }
    public void setRowsCount(int rowsCount) { this.rowsCount = rowsCount; }
    public int getColsCount() { return colsCount; }
    public void setColsCount(int colsCount) { this.colsCount = colsCount; }
}