package com.project.neobis_back_neotour.models;

import com.project.neobis_back_neotour.entities.Tour;
import com.project.neobis_back_neotour.entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewDto {

    Long id;

    @NotBlank(message = "Tour is required")
    Tour tour;

    @NotBlank(message = "User is required")
    User user;

    @DecimalMin(value = "1")
    @DecimalMax(value = "5")
    @NotBlank(message = "Rating is required")
    Integer rating;

    String comment;

    LocalDateTime created_at;

    LocalDateTime updated_at;
}