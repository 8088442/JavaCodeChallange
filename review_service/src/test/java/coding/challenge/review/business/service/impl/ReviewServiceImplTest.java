package coding.challenge.review.business.service.impl;

import coding.challenge.review.api.model.ReviewBriefDto;
import coding.challenge.review.business.mapper.ReviewMapper;
import coding.challenge.review.persistence.domain.ReviewBriefProjection;
import coding.challenge.review.persistence.repository.ReviewRepository;
import coding.challenge.review.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @InjectMocks private ReviewServiceImpl reviewService;
    @Mock private ReviewRepository reviewRepository;
    @Mock private ReviewMapper mapper;

    @Test
    void getReviewBriefIfThereIsReviewInDb() {
        final ReviewBriefProjection reviewBriefProjection = TestUtil.generateReviewBriefProjection();
        when(reviewRepository.findBriefByProductId(any())).thenReturn(reviewBriefProjection);
        final ReviewBriefDto actual = reviewService.getReviewBrief(reviewBriefProjection.getProductId());
        assertThat(actual).isNull();
    }

    @Test
    void getReviewBriefIfNoReviews() {
        when(reviewRepository.findBriefByProductId(any())).thenReturn(null);
        final ReviewBriefDto actual = reviewService.getReviewBrief("ABCD");
        assertThat(actual).isNull();
    }
}