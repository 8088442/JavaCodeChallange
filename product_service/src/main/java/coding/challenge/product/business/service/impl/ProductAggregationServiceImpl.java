package coding.challenge.product.business.service.impl;

import coding.challenge.product.api.model.ReviewAggregateDto;
import coding.challenge.product.business.service.ProductAggregationService;
import coding.challenge.product.client.AdidasClient;
import coding.challenge.product.client.ReviewClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductAggregationServiceImpl implements ProductAggregationService {
    private final AdidasClient adidasClient;
    private final ReviewClient reviewClient;
    private final ObjectMapper objectMapper;

    @Override
    public ReviewAggregateDto aggregateProduct(String productId) {
        log.debug("Start aggregate product");

        Map<String, Object> product = Map.of();
        Map<String, Object> review = Map.of();
        try {
            product = objectMapper.readValue(adidasClient.getProductDescription(productId), new TypeReference<>() {
            });
        } catch (Exception ignored) {}

        try {
            review = reviewClient.getReview(productId);
        } catch (Exception ignored) {}

        return new ReviewAggregateDto(product, review);
    }
}
