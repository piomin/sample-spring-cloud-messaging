package pl.piomin.services.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.piomin.services.account.model.Product;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping("/ids")
    List<Product> findByIds(@RequestBody List<Long> ids);

}
