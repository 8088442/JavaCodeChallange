package coding.challenge.product.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record ReviewBriefDto(
        String productId,
        Integer averageReviewScore,
        Integer numberOfReviews
) implements Serializable {
}
