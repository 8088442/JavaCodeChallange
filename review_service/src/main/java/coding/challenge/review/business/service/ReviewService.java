package coding.challenge.review.business.service;

import coding.challenge.review.api.model.ReviewBriefDto;
import coding.challenge.review.api.model.SaveReviewDto;

import java.util.UUID;

public interface ReviewService {
    ReviewBriefDto getReviewBrief(String productId);
    UUID saveNewReview(SaveReviewDto reviewDto);
    void updateReview(UUID reviewId, SaveReviewDto reviewDto);
    void deleteReview(UUID reviewId);
    void deleteAllProductReviews(String productId);
}
