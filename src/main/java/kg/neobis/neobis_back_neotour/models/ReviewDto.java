package kg.neobis.neobis_back_neotour.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;

import kg.neobis.neobis_back_neotour.base.BaseDto;
import kg.neobis.neobis_back_neotour.entities.Tour;
import kg.neobis.neobis_back_neotour.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewDto extends BaseDto {

    Tour tour;

    User user;

    @DecimalMin(value = "1")
    @DecimalMax(value = "5")
    Integer rating;

    String comment;

    @Builder
    public ReviewDto(Long id, LocalDateTime created_at, LocalDateTime updated_at, Tour tour, User user, Integer rating, String comment) {
        super(id, created_at, updated_at);
        this.tour = tour;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }
}