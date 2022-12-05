package coding.challenge.review.persistence.repository;

import coding.challenge.review.persistence.domain.ReviewBriefProjection;
import coding.challenge.review.persistence.domain.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {
    void deleteByProductId(String productId);

    @Query("select r.productId as productId, avg(r.reviewScore) as averageReviewScore, count(r.id) as numberOfReviews " +
            "from ReviewEntity as r where r.productId = :product_id group by r.productId")
    ReviewBriefProjection findBriefByProductId(@Param("product_id") String productId);
}
