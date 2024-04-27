package kg.neobis.neobis_back_neotour.services;

import kg.neobis.neobis_back_neotour.models.TourDto;

import java.util.List;

public interface TourService {

    TourDto createTour(TourDto tourDto);

    List<TourDto> getAllTours();

    TourDto getTourById(Long id);

    List<TourDto> getPopularTours();

    List<TourDto> getFeaturedTours();

    List<TourDto> getMostVisitedTours();

    List<TourDto> getToursByContinent(String continent);

    List<TourDto> getRecommendedTours(String season);

    TourDto updateTour(Long id, TourDto tourDto);

    void deleteTour(Long id);
}
