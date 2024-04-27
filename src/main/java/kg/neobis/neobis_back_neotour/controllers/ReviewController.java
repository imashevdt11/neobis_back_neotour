package kg.neobis.neobis_back_neotour.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import kg.neobis.neobis_back_neotour.commons.EndpointConstants;
import kg.neobis.neobis_back_neotour.models.ReviewDto;
import kg.neobis.neobis_back_neotour.services.ReviewService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@Tag(name = "Review")
@RequestMapping(EndpointConstants.REVIEW_ENDPOINT)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewController {

    ReviewService reviewService;

    @Operation(
            description = "adding review",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Review created successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request due to validation error"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<Object> createReview(@Valid @RequestBody ReviewDto review, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        ReviewDto createdReview = reviewService.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @Operation(
            description = "getting all existed reviews",
            responses = @ApiResponse(responseCode = "200", description = "List of reviews retrieved successfully")
    )
    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @Operation(
            description = "getting review by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Review retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Review not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
        ReviewDto review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    @Operation(
            description = "getting all reviews for tour",
            responses = @ApiResponse(responseCode = "200", description = "List of reviews for tour retrieved successfully")
    )
    @GetMapping("/tour/{tourId}")
    public ResponseEntity<List<ReviewDto>> getToursReviewsId(@PathVariable Long tourId) {
        List<ReviewDto> reviews = reviewService.getToursReviews(tourId);
        return ResponseEntity.ok(reviews);
    }

    @Operation(
            description = "deleting review by id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Review deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Review not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}