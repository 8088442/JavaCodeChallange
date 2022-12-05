package coding.challenge.review.persistence.domain;

public interface ReviewBriefProjection {
    String getProductId();
    Integer getAverageReviewScore();
    Integer getNumberOfReviews();
}
