package coding.challenge.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "adidas", url = "${app.client.base-url.adidas}")
public interface AdidasClient {

    @GetMapping("/{product_id}")
    String getProductDescription(@PathVariable("product_id") String productId);
}
