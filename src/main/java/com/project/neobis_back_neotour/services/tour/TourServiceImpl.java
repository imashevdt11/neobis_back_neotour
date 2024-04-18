package com.project.neobis_back_neotour.services.tour;

import com.project.neobis_back_neotour.entities.Tour;
import com.project.neobis_back_neotour.exceptions.TourNotFoundException;
import com.project.neobis_back_neotour.models.TourDto;
import com.project.neobis_back_neotour.repositories.TourRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourServiceImpl implements TourService {

    TourRepository tourRepository;

    @Override
    public TourDto createTour(TourDto tourDto) {
        Tour tour = Tour.builder()
                .name(tourDto.getName())
                .description(tourDto.getDescription())
                .image_url(tourDto.getImage_url())
                .price(tourDto.getPrice())
                .city(tourDto.getCity())
                .country(tourDto.getCountry())
                .continent(tourDto.getContinent())
                .build();

        Tour savedTour = tourRepository.save(tour);
        return convertToUserDto(savedTour);
    }

    @Override
    public List<TourDto> getAllTours() {
        List<Tour> tourList = tourRepository.findAll();
        return tourList.stream().map(this::convertToUserDto).toList();
    }

    @Override
    public TourDto getTourById(Long id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException("Tour not found with id: " + id, HttpStatus.NOT_FOUND.value()));
        return convertToUserDto(tour);
    }

    @Override
    public TourDto updateTour(Long id, TourDto tourDto) {

        TourDto tour = getTourById(id);

        if (tour == null) {
            throw new TourNotFoundException("Tour not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }

        tour.setName(tourDto.getName());
        tour.setDescription(tourDto.getDescription());
        tour.setImage_url(tourDto.getImage_url());
        tour.setPrice(tourDto.getPrice());
        tour.setCity(tourDto.getCity());
        tour.setContinent(tourDto.getCountry());

        Tour updatedTour = tourRepository.save(convertToTourEntity(tour));
        return convertToUserDto(updatedTour);
    }

    @Override
    public void deleteTour(Long id) {
        Optional<Tour> optionalTour = tourRepository.findById(id);

        if (optionalTour.isPresent()) {
            tourRepository.deleteById(id);
        } else {
            throw new TourNotFoundException("Tour not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }
    }

    public TourDto convertToUserDto(Tour tour) {
        return TourDto.builder()
                .id(tour.getId())
                .name(tour.getName())
                .description(tour.getDescription())
                .image_url(tour.getImage_url())
                .price(tour.getPrice())
                .city(tour.getCity())
                .country(tour.getCountry())
                .continent(tour.getContinent())
                .created_at(tour.getCreated_at())
                .updated_at(tour.getUpdated_at())
                .build();
    }

    public Tour convertToTourEntity(TourDto tourDto) {
        return Tour.builder()
                .id(tourDto.getId())
                .name(tourDto.getName())
                .description(tourDto.getDescription())
                .image_url(tourDto.getImage_url())
                .price(tourDto.getPrice())
                .city(tourDto.getCity())
                .country(tourDto.getCountry())
                .continent(tourDto.getContinent())
                .created_at(tourDto.getCreated_at())
                .updated_at(tourDto.getUpdated_at())
                .build();
    }
}