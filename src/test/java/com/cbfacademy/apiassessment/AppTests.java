package com.cbfacademy.apiassessment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private List<Payment> defaultPayments = new ArrayList<>() {
		{
			add(new Payment(new BigDecimal(3000), new BigDecimal(2000), "1111 2222 3333 4444", "Tomike", 123));
			add(new Payment(new BigDecimal(4000), new BigDecimal(2000), "1141 2922 3338 4744", "Kwame", 178));

		}
	};

	private URI baseURI;

	@Autowired
	private ListPaymentService listPaymentService;

	@BeforeEach
	public void setUp() throws Exception {
		this.baseURI = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("localhost")
				.port(port)
				.path("api/payment")
				.build()
				.toUri();

		for (Iterator<Payment> it = listPaymentService.getAllpayments().iterator(); it.hasNext();) {
			it.next();
			it.remove();
		}

		for (Payment payments : defaultPayments) {
			listPaymentService.createPayment(payments);
		}
	}

	@Test
	@Description("POST /api/ious creates new Payment")
	public void testCreatePayment() {
		Payment payment = new Payment(new BigDecimal(4000), new BigDecimal(2000), "1911 2202 3393 4441", "Tomike", 123);
		ResponseEntity<Payment> response = restTemplate.postForEntity(baseURI.toString(), payment, Payment.class);

		// assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertNotNull(response.getBody().getId());
	}

	@Test
	@Description("GET /api/ious returns all Payments")
	public void testGetAllpayments() throws URISyntaxException {
		ResponseEntity<List<Payment>> response = restTemplate.exchange(baseURI, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Payment>>() {
				});
		List<Payment> responcePayments = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(responcePayments);
		assertTrue(defaultPayments.size() == responcePayments.size());
	}

}
