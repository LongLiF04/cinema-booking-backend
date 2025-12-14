package com.example.CineBook.repository.irepository;

import com.example.CineBook.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findByBookingId(UUID bookingId);
    List<Ticket> findByShowtimeId(UUID showtimeId);
    void deleteByBookingId(UUID bookingId);
    boolean existsBySeatIdAndShowtimeId(UUID seatId, UUID showtimeId);
}
