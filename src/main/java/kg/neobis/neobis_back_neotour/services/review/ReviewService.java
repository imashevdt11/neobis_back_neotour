package kg.neobis.neobis_back_neotour.services.review;

import kg.neobis.neobis_back_neotour.models.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewDto reviewDto);

    List<ReviewDto> getAllReviews();

    ReviewDto getReviewById(Long id);

    List<ReviewDto> getReviewsByTourId(Long tourId);

    ReviewDto updateReview(Long id, ReviewDto reviewDto);

    void deleteReview(Long id);
}