package kg.neobis.neobis_back_neotour.models;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;

import kg.neobis.neobis_back_neotour.base.BaseDto;
import kg.neobis.neobis_back_neotour.entities.Tour;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDto extends BaseDto {

    Tour tour;

    @NotBlank(message = "Phone number is required")
    String phone_number;

    @DecimalMin(value = "1")
    @DecimalMax(value = "10")
    Integer number_of_tourists;

    String comment;

    @Builder
    public BookingDto(Long id, LocalDateTime created_at, LocalDateTime updated_at, Tour tour, String phone_number, Integer number_of_tourists, String comment) {
        super(id, created_at, updated_at);
        this.tour = tour;
        this.phone_number = phone_number;
        this.number_of_tourists = number_of_tourists;
        this.comment = comment;
    }
}