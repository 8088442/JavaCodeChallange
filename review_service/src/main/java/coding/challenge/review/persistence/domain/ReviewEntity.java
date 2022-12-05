package coding.challenge.review.persistence.domain;

import coding.challenge.common.persistence.AbstractUuidIdEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@SuppressWarnings("java:S2160")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@Table(name = "review")
public class ReviewEntity extends AbstractUuidIdEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 8047775352532295295L;

    @NotBlank
    @Column(name = "product_id", nullable = false, updatable = false)
    private String productId;

    @NotNull
    @Range(min = 1, max = 5)
    @Column(name = "score")
    private Integer reviewScore;

    @Column(name = "text")
    private String reviewText;
}
