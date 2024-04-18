package com.project.neobis_back_neotour.controllers;

import com.project.neobis_back_neotour.commons.EndpointConstants;
import com.project.neobis_back_neotour.models.BookingDto;
import com.project.neobis_back_neotour.services.booking.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@Tag(name = "Booking")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(EndpointConstants.BOOKING_ENDPOINT)
public class BookingController {

    BookingService bookingService;

    @Operation(
            description = "Create a new review",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Review created successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request due to validation error"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<Object> createBooking(@Valid @RequestBody BookingDto booking, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        BookingDto createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    @Operation(
            description = "Get all bookings",
            responses = @ApiResponse(responseCode = "200", description = "List of reviews retrieved successfully")
    )
    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        List<BookingDto> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @Operation(
            description = "Get booking by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Booking retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Booking not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) {
        BookingDto booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @Operation(
            description = "Update booking by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Booking updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request due to validation error"),
                    @ApiResponse(responseCode = "404", description = "Booking not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateBooking(@PathVariable @Positive Long id, @Valid @RequestBody BookingDto booking, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        BookingDto savedBooking = bookingService.updateBooking(id, booking);
        return ResponseEntity.ok(savedBooking);
    }

    @Operation(
            description = "Booking review by ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Booking deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Booking not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}