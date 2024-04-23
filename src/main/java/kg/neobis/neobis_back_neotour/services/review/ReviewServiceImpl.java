package kg.neobis.neobis_back_neotour.services.review;

import kg.neobis.neobis_back_neotour.entities.Review;
import kg.neobis.neobis_back_neotour.entities.Tour;
import kg.neobis.neobis_back_neotour.entities.User;
import kg.neobis.neobis_back_neotour.exceptions.ReviewNotFoundException;
import kg.neobis.neobis_back_neotour.exceptions.TourNotFoundException;
import kg.neobis.neobis_back_neotour.exceptions.UserNotFoundException;
import kg.neobis.neobis_back_neotour.mappers.ReviewMapper;
import kg.neobis.neobis_back_neotour.models.ReviewDto;
import kg.neobis.neobis_back_neotour.repositories.ReviewRepository;
import kg.neobis.neobis_back_neotour.repositories.TourRepository;
import kg.neobis.neobis_back_neotour.repositories.UserRepository;
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
    UserRepository userRepository;
    TourRepository tourRepository;

    @Override
    public ReviewDto addReview(ReviewDto reviewDto) {

        User user = userRepository.findById(reviewDto.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + reviewDto.getUser().getId(), HttpStatus.NOT_FOUND.value()));

        Tour tour = tourRepository.findById(reviewDto.getTour().getId())
                .orElseThrow(() -> new TourNotFoundException("Tour not found with id: " + reviewDto.getTour().getId(), HttpStatus.NOT_FOUND.value()));

        Review review = Review.builder()
                .user(user)
                .tour(tour)
                .rating(reviewDto.getRating())
                .comment(reviewDto.getComment())
                .build();
        Review savedReview = reviewRepository.save(review);
        return ReviewMapper.toDto(savedReview);
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        List<Review> reviewList = reviewRepository.findAll();
        return reviewList.stream().map(ReviewMapper::toDto).toList();
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id, HttpStatus.NOT_FOUND.value()));
        return ReviewMapper.toDto(review);
    }

    @Override
    public List<ReviewDto> getToursReviews(Long tourId) {
        List<Review> reviewList = reviewRepository.findByTourId(tourId);
        return reviewList.stream().map(ReviewMapper::toDto).toList();
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
}