package coding.challenge.review.business.mapper;

import coding.challenge.review.api.model.ReviewBriefDto;
import coding.challenge.review.api.model.SaveReviewDto;
import coding.challenge.review.persistence.domain.ReviewBriefProjection;
import coding.challenge.review.persistence.domain.ReviewEntity;
import coding.challenge.review.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class ReviewMapperTest {

    private ReviewMapper mapper;
    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(ReviewMapper.class);
    }

    @Test
    void mapReviewEntityToGetReviewDto() {
        // GIVEN
        final ReviewBriefProjection reviewBriefProjection = TestUtil.generateReviewBriefProjection();
        // WHEN
        final ReviewBriefDto actual = mapper.mapReviewEntityToGetReviewDto(reviewBriefProjection);
        // THEN
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.productId()).isEqualTo(reviewBriefProjection.getProductId()),
                () -> assertThat(actual.numberOfReviews()).isEqualTo(reviewBriefProjection.getNumberOfReviews()),
                () -> assertThat(actual.averageReviewScore()).isEqualTo(reviewBriefProjection.getAverageReviewScore())
        );
    }

    @Test
    void mapSaveReviewDtoToReviewEntity() {
        // GIVEN
        final SaveReviewDto saveReviewDto = TestUtil.generateSaveReviewDto();
        // WHEN
        final ReviewEntity actual = mapper.mapSaveReviewDtoToReviewEntity(saveReviewDto);
        // THEN
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getProductId()).isEqualTo(saveReviewDto.productId()),
                () -> assertThat(actual.getReviewScore()).isEqualTo(saveReviewDto.reviewScore()),
                () -> assertThat(actual.getReviewText()).isEqualTo(saveReviewDto.reviewText())
        );
    }
}