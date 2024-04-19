package com.project.neobis_back_neotour.models;

import com.project.neobis_back_neotour.entities.Tour;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDto {

    Long id;

    @NotBlank(message = "Tour is required")
    Tour tour;

    @NotBlank(message = "Phone number is required")
    String phone_number;

    @DecimalMin(value = "1")
    Integer number_of_tourists;

    String comment;

    BigDecimal total_price;

    LocalDateTime booking_date;
    LocalDateTime created_at;
    LocalDateTime updated_at;
}