package coding.challenge.review.business.service.impl;

import coding.challenge.common.api.exception.ResourceNotFoundException;
import coding.challenge.review.api.model.ReviewBriefDto;
import coding.challenge.review.api.model.SaveReviewDto;
import coding.challenge.review.business.mapper.ReviewMapper;
import coding.challenge.review.business.service.ReviewService;
import coding.challenge.review.persistence.domain.ReviewEntity;
import coding.challenge.review.persistence.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Transactional(readOnly = true)
    @Override
    public ReviewBriefDto getReviewBrief(String productId) {
        log.debug("Retrieve review brief by product ID");

        return reviewMapper.mapReviewEntityToGetReviewDto(reviewRepository.findBriefByProductId(productId));
    }

    @Override
    public UUID saveNewReview(SaveReviewDto reviewDto) {
        log.debug("Create new review");

        final ReviewEntity reviewEntity = reviewMapper.mapSaveReviewDtoToReviewEntity(reviewDto);
        return reviewRepository.save(reviewEntity).getId();
    }

    @Override
    public void updateReview(@NonNull UUID reviewId, @NonNull SaveReviewDto reviewDto) {
        log.debug("Update existing review");

        final ReviewEntity reviewEntity = reviewRepository
                .findById(reviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Review", reviewId.toString())
                );
        reviewEntity.setProductId(reviewDto.productId());
        reviewEntity.setReviewScore(reviewDto.reviewScore());
        reviewEntity.setReviewText(reviewDto.reviewText());
        reviewRepository.save(reviewEntity);
    }

    @Override
    public void deleteReview(@NonNull UUID reviewId) {
        log.debug("Delete review by ID");

        reviewRepository.deleteById(reviewId);
    }

    @Override
    public void deleteAllProductReviews(@NonNull String productId) {
        log.debug("Delete all reviews by product ID");

        reviewRepository.deleteByProductId(productId);
    }
}
