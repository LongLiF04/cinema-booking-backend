package com.example.CineBook.service.impl;

import com.example.CineBook.common.exception.BusinessException;
import com.example.CineBook.common.exception.MessageCode;
import com.example.CineBook.dto.ticket.TicketCreateRequest;
import com.example.CineBook.dto.ticket.TicketResponse;
import com.example.CineBook.model.Ticket;
import com.example.CineBook.repository.irepository.TicketRepository;
import com.example.CineBook.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    @Transactional
    public TicketResponse createTicket(TicketCreateRequest request) {
        Ticket ticket = Ticket.builder()
                .bookingId(request.getBookingId())
                .seatId(request.getSeatId())
                .showtimeId(request.getShowtimeId())
                .price(request.getPrice())
                .build();

        Ticket saved = ticketRepository.save(ticket);
        return toResponse(saved);
    }

    @Override
    public List<TicketResponse> getTicketsByBookingId(UUID bookingId) {
        return ticketRepository.findByBookingId(bookingId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketResponse> getTicketsByShowtimeId(UUID showtimeId) {
        return ticketRepository.findByShowtimeId(showtimeId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteTicket(UUID id) {
        if (!ticketRepository.existsById(id)) {
            throw new BusinessException(MessageCode.TICKET_NOT_FOUND);
        }
        ticketRepository.deleteById(id);
    }

    private TicketResponse toResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .bookingId(ticket.getBookingId())
                .seatId(ticket.getSeatId())
                .showtimeId(ticket.getShowtimeId())
                .price(ticket.getPrice())
                .build();
    }
}
