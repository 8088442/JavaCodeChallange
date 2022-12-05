package coding.challenge.product.api.controller;

import coding.challenge.product.api.model.ReviewAggregateDto;
import coding.challenge.product.business.service.ProductAggregationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductAggregationService productAggregationService;

    @GetMapping(value = "/{product_id}")
    public ResponseEntity<ReviewAggregateDto> getProductAggregate(@PathVariable("product_id") final String productId) {
        return ResponseEntity.ok(productAggregationService.aggregateProduct(productId));
    }
}
