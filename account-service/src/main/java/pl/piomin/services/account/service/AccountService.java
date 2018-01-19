package pl.piomin.services.account.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.piomin.services.account.model.Order;

@Service
public class AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public void process(Order order) throws JsonProcessingException {
		LOGGER.info("Order processed: {}", mapper.writeValueAsString(order));
	}
	
}
