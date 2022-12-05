package coding.challenge.review.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record SaveReviewDto(
        @NotBlank String productId,
        @NotNull @Range(min = 1, max = 5) Integer reviewScore,
        String reviewText
) implements Serializable {
}
