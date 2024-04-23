package kg.neobis.neobis_back_neotour.models;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

import kg.neobis.neobis_back_neotour.base.BaseDto;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourDto extends BaseDto {

    @NotBlank(message = "Name is required")
    @Size(min = 10, max = 50)
    String name;

    @NotBlank(message = "Description is required")
    String description;

    @NotBlank(message = "Image is required")
    String image_url;

    @NotBlank(message = "City is required")
    String city;

    @NotBlank(message = "Country is required")
    String country;

    @NotBlank(message = "Continent is required")
    String continent;

    @NotBlank(message = "Season is required")
    String season;

    Integer views;

    @Builder
    public TourDto(Long id, LocalDateTime created_at, LocalDateTime updated_at, String name, String description, String image_url,
                   String city, String country, String continent, String season, Integer views) {
        super(id, created_at, updated_at);
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.city = city;
        this.country = country;
        this.continent = continent;
        this.season = season;
        this.views = views;
    }
}