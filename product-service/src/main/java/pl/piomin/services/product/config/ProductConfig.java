package pl.piomin.services.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.piomin.services.product.model.Product;
import pl.piomin.services.product.repository.ProductRepository;

@Configuration
public class ProductConfig {

    @Bean
    ProductRepository repository() {
        ProductRepository repository = new ProductRepository();
        repository.add(new Product("Test1", 1000));
        repository.add(new Product("Test2", 1500));
        repository.add(new Product("Test3", 2000));
        repository.add(new Product("Test4", 3000));
        repository.add(new Product("Test5", 1300));
        repository.add(new Product("Test6", 2700));
        repository.add(new Product("Test7", 3500));
        repository.add(new Product("Test8", 1250));
        repository.add(new Product("Test9", 2450));
        repository.add(new Product("Test10", 800));
        return repository;
    }

}
