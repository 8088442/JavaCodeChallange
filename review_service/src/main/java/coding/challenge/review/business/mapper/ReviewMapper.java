package coding.challenge.review.business.mapper;

import coding.challenge.review.api.model.ReviewBriefDto;
import coding.challenge.review.api.model.SaveReviewDto;
import coding.challenge.review.persistence.domain.ReviewBriefProjection;
import coding.challenge.review.persistence.domain.ReviewEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface ReviewMapper {

    ReviewBriefDto mapReviewEntityToGetReviewDto(ReviewBriefProjection reviewBriefProjection);

    //    @Mapping(target = "id", ignore = true)
    ReviewEntity mapSaveReviewDtoToReviewEntity(SaveReviewDto saveReviewDto);
}
