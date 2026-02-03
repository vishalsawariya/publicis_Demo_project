package com.movieticket.util;

public class SeatUtils {
    public static String seatLabel(int rowIndex, int colIndex) {
        char rowChar = (char)('A' + rowIndex);
        return rowChar + String.valueOf(colIndex + 1);
    }
}