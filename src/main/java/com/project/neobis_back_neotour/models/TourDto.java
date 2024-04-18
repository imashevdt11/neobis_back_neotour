package com.project.neobis_back_neotour.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourDto {

    Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 10, max = 50)
    String name;

    @NotBlank(message = "Description is required")
    String description;

    @NotBlank(message = "Image is required")
    String image_url;

    @NotBlank(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    BigDecimal price;

    @NotBlank(message = "City is required")
    String city;

    @NotBlank(message = "Country is required")
    String country;

    @NotBlank(message = "Continent is required")
    String continent;

    Integer views;

    LocalDateTime created_at;

    LocalDateTime updated_at;
}