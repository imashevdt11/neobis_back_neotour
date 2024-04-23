package kg.neobis.neobis_back_neotour.mappers;

import kg.neobis.neobis_back_neotour.entities.Review;
import kg.neobis.neobis_back_neotour.models.ReviewDto;

public class ReviewMapper {
    public static ReviewDto toDto(Review review) {
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
}