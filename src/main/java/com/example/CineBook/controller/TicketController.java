package com.example.CineBook.controller;

import com.example.CineBook.common.response.ApiResponse;
import com.example.CineBook.dto.ticket.TicketCreateRequest;
import com.example.CineBook.dto.ticket.TicketResponse;
import com.example.CineBook.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@Tag(name = "Ticket Management", description = "APIs quản lý vé")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    @Operation(summary = "Tạo ticket mới")
    public ResponseEntity<ApiResponse<TicketResponse>> createTicket(@Valid @RequestBody TicketCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo ticket thành công", ticketService.createTicket(request)));
    }

    @GetMapping("/booking/{bookingId}")
    @Operation(summary = "Lấy danh sách ticket theo booking ID")
    public ResponseEntity<ApiResponse<List<TicketResponse>>> getTicketsByBookingId(@PathVariable UUID bookingId) {
        return ResponseEntity.ok(ApiResponse.success(ticketService.getTicketsByBookingId(bookingId)));
    }

    @GetMapping("/showtime/{showtimeId}")
    @Operation(summary = "Lấy danh sách ticket theo showtime ID")
    public ResponseEntity<ApiResponse<List<TicketResponse>>> getTicketsByShowtimeId(@PathVariable UUID showtimeId) {
        return ResponseEntity.ok(ApiResponse.success(ticketService.getTicketsByShowtimeId(showtimeId)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa ticket")
    public ResponseEntity<ApiResponse<Void>> deleteTicket(@PathVariable UUID id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa ticket thành công", null));
    }
}
