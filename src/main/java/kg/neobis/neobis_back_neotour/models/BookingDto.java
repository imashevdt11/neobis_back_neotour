package kg.neobis.neobis_back_neotour.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;

import kg.neobis.neobis_back_neotour.entities.Tour;
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
public class BookingDto {

    Long id;

    Tour tour;

    @NotBlank(message = "Phone number is required")
    String phone_number;

    @DecimalMin(value = "1")
    Integer number_of_tourists;

    String comment;

    LocalDateTime booking_date;
    LocalDateTime created_at;
    LocalDateTime updated_at;
}