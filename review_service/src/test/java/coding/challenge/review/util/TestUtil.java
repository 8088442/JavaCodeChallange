package coding.challenge.review.util;

import coding.challenge.review.api.model.ReviewBriefDto;
import coding.challenge.review.api.model.SaveReviewDto;
import coding.challenge.review.persistence.domain.ReviewBriefProjection;
import coding.challenge.review.persistence.domain.ReviewEntity;
import lombok.experimental.UtilityClass;
import net.datafaker.Faker;


@UtilityClass
public class TestUtil {
    public ReviewEntity generateReviewEntity() {
        final Faker faker = new Faker();
        return ReviewEntity.builder()
                .productId(faker.code().asin())
                .reviewScore(faker.random().nextInt(1, 5))
                .reviewText(faker.marketing().buzzwords())
                .build();
    }

    public ReviewBriefProjection generateReviewBriefProjection() {
        final Faker faker = new Faker();
        final String productId = faker.code().asin();
        final Integer aveRevScore = faker.random().nextInt(1, 5);
        final Integer reviewNum = faker.random().nextInt(1, 100);

        return new ReviewBriefProjection() {
            @Override
            public String getProductId() {
                return productId;
            }

            @Override
            public Integer getAverageReviewScore() {
                return aveRevScore;
            }

            @Override
            public Integer getNumberOfReviews() {
                return reviewNum;
            }
        };
    }

    public SaveReviewDto generateSaveReviewDto(){
        final Faker faker = new Faker();

        return new SaveReviewDto(
                faker.code().asin(),
                faker.random().nextInt(1, 5),
                faker.lorem().sentence()
        );
    }

    public ReviewBriefDto generateReviewBriefDto(){
        final Faker faker = new Faker();

        return new ReviewBriefDto(
                faker.code().asin(),
                faker.random().nextInt(1, 5),
                faker.random().nextInt(1, 100)
        );
    }
}
