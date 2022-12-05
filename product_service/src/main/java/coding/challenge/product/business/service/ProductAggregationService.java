package coding.challenge.product.business.service;

import coding.challenge.product.api.model.ReviewAggregateDto;

public interface ProductAggregationService {
    ReviewAggregateDto aggregateProduct(String productId);
}
