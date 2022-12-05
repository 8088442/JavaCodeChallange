package coding.challenge.review.api.controller;

import coding.challenge.review.api.model.ReviewBriefDto;
import coding.challenge.review.api.model.SaveReviewDto;
import coding.challenge.review.business.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{product_id}")
    public ResponseEntity<ReviewBriefDto> getReviewBriefByProduct(@PathVariable("product_id") final String productId) {
        return ResponseEntity.ok(reviewService.getReviewBrief(productId));
    }

    @Secured("ROLE_EDITOR")
    @PostMapping("/")
    public ResponseEntity<String> createReview(@RequestBody @Validated final SaveReviewDto reviewDto) {
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{review_id}")
                        .buildAndExpand(reviewService.saveNewReview(reviewDto))
                        .toUri()
        ).build();
    }

    @Secured("ROLE_EDITOR")
    @PutMapping("/{review_id}")
    public ResponseEntity<String> updateReview(
            @PathVariable("review_id") final UUID reviewId,
            @RequestBody @Validated final SaveReviewDto reviewDto
    ) {
        reviewService.updateReview(reviewId, reviewDto);
        return ResponseEntity.noContent().build();
    }

    @Secured("ROLE_EDITOR")
    @DeleteMapping("/{review_id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("review_id") final UUID reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    @Secured("ROLE_EDITOR")
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteReviewsByProduct(@RequestParam(value = "product_id") @NotBlank final String productId) {
        reviewService.deleteAllProductReviews(productId);
        return ResponseEntity.noContent().build();
    }
}
