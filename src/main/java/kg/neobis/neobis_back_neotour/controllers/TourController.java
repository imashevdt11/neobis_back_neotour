package kg.neobis.neobis_back_neotour.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import kg.neobis.neobis_back_neotour.commons.EndpointConstants;
import kg.neobis.neobis_back_neotour.models.TourDto;
import kg.neobis.neobis_back_neotour.services.tour.TourService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@Tag(name = "Tour")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(EndpointConstants.TOUR_ENDPOINT)
public class TourController {

    TourService tourService;

    @Operation(
            description = "creating new tour",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tour created successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request due to validation error"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<Object> createTour(@Valid @RequestBody TourDto tour, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        TourDto createdTour = tourService.createTour(tour);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTour);
    }

    @Operation(
            description = "getting all existed tours",
            responses = @ApiResponse(responseCode = "200", description = "List of tours retrieved successfully")
    )
    @GetMapping
    public ResponseEntity<List<TourDto>> getAllTours() {
        List<TourDto> tours = tourService.getAllTours();
        return ResponseEntity.ok(tours);
    }

    @Operation(
            description = "getting tour by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tour retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Tour not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TourDto> getTourById(@PathVariable Long id) {
        TourDto tour = tourService.getTourById(id);
        return ResponseEntity.ok(tour);
    }

    @Operation(
            description = "getting 5 most popular tours",
            responses = @ApiResponse(responseCode = "200", description = "List of popular tours retrieved successfully")
    )
    @GetMapping("/popular")
    public ResponseEntity<List<TourDto>> getPopularTours() {
        List<TourDto> popularTours = tourService.getPopularTours();
        return ResponseEntity.ok(popularTours);
    }

    @Operation(
            description = "getting 5 tours with biggest average rating",
            responses = @ApiResponse(responseCode = "200", description = "List of popular tours retrieved successfully")
    )
    @GetMapping("/featured")
    public ResponseEntity<List<TourDto>> getFeaturedTours() {
        List<TourDto> featuredTours = tourService.getFeaturedTours();
        return ResponseEntity.ok(featuredTours);
    }

    @Operation(
            description = "getting most visited tours",
            responses = @ApiResponse(responseCode = "200", description = "List of most viewed tours retrieved successfully")
    )
    @GetMapping("/most-visited")
    public ResponseEntity<List<TourDto>> getMostVisitedTours() {
        List<TourDto> mostVisitedTours = tourService.getMostVisitedTours();
        return ResponseEntity.ok(mostVisitedTours);
    }

    @Operation(
            description = "getting tours by continent",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of tours retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "No tours found with specified continent"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<TourDto>> getToursByContinent(@PathVariable String continent) {
        List<TourDto> tourList = tourService.getToursByContinent(continent);
        return ResponseEntity.ok(tourList);
    }

    @Operation(
            description = "getting tours by current season",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of tours retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "No tours found with specified season"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/recommended")
    public ResponseEntity<List<TourDto>> getRecommended() {
        String currentSeason = getCurrentSeason();
        List<TourDto> tourList = tourService.getRecommendedTours(currentSeason);
        return ResponseEntity.ok(tourList);
    }

    private String getCurrentSeason() {
        int month = LocalDate.now().getMonthValue();
        return switch (month) {
            case 12, 1, 2 -> "Winter";
            case 3, 4, 5 -> "Spring";
            case 6, 7, 8 -> "Summer";
            case 9, 10, 11 -> "Autumn";
            default -> "Unknown";
        };
    }

    @Operation(
            description = "updating tour's info by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tour updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request due to validation error"),
                    @ApiResponse(responseCode = "404", description = "Tour not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateTour(@PathVariable @Positive Long id, @Valid @RequestBody TourDto tour, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        TourDto savedTour = tourService.updateTour(id, tour);
        return ResponseEntity.ok(savedTour);
    }

    @Operation(
            description = "deleting tour by ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Tour deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Tour not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }
}