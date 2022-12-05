package coding.challenge.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(value = "review", url = "${app.client.base-url.review}")
public interface ReviewClient {

    @GetMapping("/{product_id}")
    Map<String, Object> getReview(@PathVariable("product_id") String productId);
}
