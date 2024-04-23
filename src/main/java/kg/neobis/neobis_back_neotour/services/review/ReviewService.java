package kg.neobis.neobis_back_neotour.services.review;

import kg.neobis.neobis_back_neotour.models.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto addReview(ReviewDto reviewDto);

    List<ReviewDto> getAllReviews();

    ReviewDto getReviewById(Long id);

    List<ReviewDto> getToursReviews(Long tourId);

    void deleteReview(Long id);
}