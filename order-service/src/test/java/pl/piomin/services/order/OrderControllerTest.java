package pl.piomin.services.order;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import pl.piomin.services.messaging.Order;
import pl.piomin.services.messaging.OrderStatus;

public class OrderControllerTest {

	TestRestTemplate template = new TestRestTemplate();

	@Test
	public void testOrder() throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			sendAndAcceptOrder();
			Thread.sleep(500);
		}
	}

	private void sendAndAcceptOrder() {
		try {
			Random r = new Random();
			Order order = new Order();
			order.setCustomerId((long) r.nextInt(3) + 1);
			order.setProductIds(Arrays.asList(new Long[] { (long) r.nextInt(10) + 1, (long) r.nextInt(10) + 1 }));
			order = template.postForObject("http://localhost:8090", order, Order.class);
			if (order.getStatus() != OrderStatus.REJECTED) {
				template.put("http://localhost:8090/{id}", null, order.getId());
			}
		} catch (Exception e) {

		}
	}
	
}
