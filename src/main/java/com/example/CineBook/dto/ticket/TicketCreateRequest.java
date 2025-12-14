package com.example.CineBook.dto.ticket;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TicketCreateRequest {
    @NotNull(message = "Booking ID không được để trống")
    private UUID bookingId;
    
    @NotNull(message = "Seat ID không được để trống")
    private UUID seatId;
    
    @NotNull(message = "Showtime ID không được để trống")
    private UUID showtimeId;
    
    private BigDecimal price;
}
