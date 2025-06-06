package pl.piomin.services.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import pl.piomin.services.account.service.AccountService;
import pl.piomin.services.messaging.Order;

import java.util.function.Consumer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AccountApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountApplication.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    AccountService service;

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Bean
    public Consumer<Order> receiveOrder(Order order) {
        return o -> {
            try {
                LOGGER.info("Order received: {}", mapper.writeValueAsString(order));
                service.process(o);
            } catch (JsonProcessingException e) {
                LOGGER.error("Error deserializing the message", e);
            }
        };
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setMaxPayloadLength(1000);
        loggingFilter.setAfterMessagePrefix("REQ:");
        return loggingFilter;
    }

//	@Bean
//	public Sampler defaultSampler() {
//		return new AlwaysSampler();
//	}

}
