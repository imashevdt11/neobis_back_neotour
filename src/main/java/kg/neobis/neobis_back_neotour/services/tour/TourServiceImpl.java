package kg.neobis.neobis_back_neotour.services.tour;

import kg.neobis.neobis_back_neotour.entities.Tour;
import kg.neobis.neobis_back_neotour.exceptions.TourNotFoundException;
import kg.neobis.neobis_back_neotour.mappers.TourMapper;
import kg.neobis.neobis_back_neotour.models.TourDto;
import kg.neobis.neobis_back_neotour.repositories.TourRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

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
                .city(tourDto.getCity())
                .country(tourDto.getCountry())
                .continent(tourDto.getContinent())
                .season(tourDto.getSeason())
                .views(tourDto.getViews())
                .build();
        Tour savedTour = tourRepository.save(tour);
        return TourMapper.toDto(savedTour);
    }

    @Override
    public List<TourDto> getAllTours() {
        List<Tour> tourList = tourRepository.findAll();
        return tourList.stream().map(TourMapper::toDto).toList();
    }

    @Override
    public TourDto getTourById(Long id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException("Tour not found with id: " + id, HttpStatus.NOT_FOUND.value()));
        return TourMapper.toDto(tour);
    }

    @Override
    public List<TourDto> getPopularTours() {
        List<Tour> tourList = tourRepository.findPopularTours();
        return tourList.stream().map(TourMapper::toDto).toList();
    }

    @Override
    public List<TourDto> getFeaturedTours() {
        List<Tour> tourList = tourRepository.findToursWithHighestAverageRating();
        return tourList.stream().map(TourMapper::toDto).toList();
    }

    @Override
    public List<TourDto> getMostVisitedTours() {
        List<Tour> tourList = tourRepository.findMostVisitedTours();
        return tourList.stream().map(TourMapper::toDto).toList();
    }

    @Override
    public List<TourDto> getToursByContinent(String continent) {
        List<Tour> tourList = tourRepository.findByContinent(continent);

        if (tourList.isEmpty()) {
            throw new TourNotFoundException("Tours in " + continent + " not found", HttpStatus.NOT_FOUND.value());
        }
        return tourList.stream().map(TourMapper::toDto).toList();
    }

    @Override
    public List<TourDto> getRecommendedTours(String season) {
        List<Tour> tourList = tourRepository.findBySeason(season);

        if (tourList.isEmpty()) {
            throw new TourNotFoundException(season + " tours not found", HttpStatus.NOT_FOUND.value());
        }
        return tourList.stream().map(TourMapper::toDto).toList();
    }

    @Override
    public TourDto updateTour(Long id, TourDto tourDto) {

        Optional<Tour> optionalTour = tourRepository.findById(id);

        if (optionalTour.isPresent()) {
            Tour tour = optionalTour.get();
            tour.setName(tourDto.getName());
            tour.setDescription(tourDto.getDescription());
            tour.setImage_url(tourDto.getImage_url());
            tour.setCity(tourDto.getCity());
            tour.setContinent(tourDto.getCountry());
            tour.setSeason(tourDto.getSeason());
            tour.setUpdated_at(LocalDateTime.now());
            Tour updatedTour = tourRepository.save(tour);
            return TourMapper.toDto(updatedTour);
        } else {
            throw new TourNotFoundException("Tour not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }
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
}