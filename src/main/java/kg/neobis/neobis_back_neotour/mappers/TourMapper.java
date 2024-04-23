package kg.neobis.neobis_back_neotour.mappers;

import kg.neobis.neobis_back_neotour.entities.Tour;
import kg.neobis.neobis_back_neotour.models.TourDto;

public class TourMapper {
    public static TourDto toDto(Tour tour) {
        return TourDto.builder()
                .id(tour.getId())
                .name(tour.getName())
                .description(tour.getDescription())
                .image_url(tour.getImage_url())
                .city(tour.getCity())
                .country(tour.getCountry())
                .continent(tour.getContinent())
                .season(tour.getSeason())
                .views(tour.getViews())
                .created_at(tour.getCreated_at())
                .updated_at(tour.getUpdated_at())
                .build();
    }
}