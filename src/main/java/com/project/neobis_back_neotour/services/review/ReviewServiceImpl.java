package com.project.neobis_back_neotour.services.review;

import com.project.neobis_back_neotour.entities.Review;
import com.project.neobis_back_neotour.exceptions.ReviewNotFoundException;
import com.project.neobis_back_neotour.models.ReviewDto;
import com.project.neobis_back_neotour.repositories.ReviewRepository;

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
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = Review.builder()
                .user(reviewDto.getUser())
                .tour(reviewDto.getTour())
                .rating(reviewDto.getRating())
                .comment(reviewDto.getComment())
                .build();
        Review savedReview = reviewRepository.save(review);
        return convertToReviewDto(savedReview);
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        List<Review> reviewList = reviewRepository.findAll();
        return reviewList.stream().map(this::convertToReviewDto).toList();
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id, HttpStatus.NOT_FOUND.value()));
        return convertToReviewDto(review);
    }

    @Override
    public List<ReviewDto> getReviewsByTourId(Long tourId) {
        List<Review> reviewList = reviewRepository.findByTourId(tourId);
        return reviewList.stream().map(this::convertToReviewDto).toList();
    }

    @Override
    public ReviewDto updateReview(Long id, ReviewDto reviewDto) {

        ReviewDto review = getReviewById(id);

        if (review == null) {
            throw new ReviewNotFoundException("Review not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }

        review.setUser(reviewDto.getUser());
        review.setTour(reviewDto.getTour());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());

        Review updatedReview = reviewRepository.save(convertToReviewEntity(review));
        return convertToReviewDto(updatedReview);
    }

    @Override
    public void deleteReview(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);

        if (optionalReview.isPresent()) {
            reviewRepository.deleteById(id);
        } else {
            throw new ReviewNotFoundException("Review not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }
    }

    public ReviewDto convertToReviewDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .user(review.getUser())
                .tour(review.getTour())
                .rating(review.getRating())
                .comment(review.getComment())
                .created_at(review.getCreated_at())
                .updated_at(review.getUpdated_at())
                .build();
    }

    public Review convertToReviewEntity(ReviewDto review) {
        return Review.builder()
                .id(review.getId())
                .user(review.getUser())
                .tour(review.getTour())
                .rating(review.getRating())
                .comment(review.getComment())
                .created_at(review.getCreated_at())
                .updated_at(review.getUpdated_at())
                .build();
    }
}