package com.project.neobis_back_neotour.services.tour;

import com.project.neobis_back_neotour.models.TourDto;

import java.util.List;

public interface TourService {

    TourDto createTour(TourDto tourDto);

    List<TourDto> getAllTours();

    TourDto getTourById(Long id);

    List<TourDto> getToursByContinent(String continent);

    List<TourDto> getPopularTours();

    List<TourDto> getMostViewedTours();

    TourDto updateTour(Long id, TourDto tourDto);

    void deleteTour(Long id);
}
