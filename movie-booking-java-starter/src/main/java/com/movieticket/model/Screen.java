package com.movieticket.model;

import jakarta.persistence.*;

@Entity
public class Screen {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int rowsCount; // number of seat rows (e.g., 10)
    private int colsCount; // number of seats per row (e.g., 15)

    @ManyToOne(optional = false)
    private Theatre theatre;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getRowsCount() { return rowsCount; }
    public void setRowsCount(int rowsCount) { this.rowsCount = rowsCount; }
    public int getColsCount() { return colsCount; }
    public void setColsCount(int colsCount) { this.colsCount = colsCount; }
    public Theatre getTheatre() { return theatre; }
    public void setTheatre(Theatre theatre) { this.theatre = theatre; }
}