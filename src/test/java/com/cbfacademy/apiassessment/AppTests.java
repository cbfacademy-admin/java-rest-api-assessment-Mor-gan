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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// @SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// class AppTests {

// 	@LocalServerPort
// 	private int port;

// 	private List<Payment> defaultPayments = new ArrayList<>() {
// 		{
// 			add(new Payment(new BigDecimal(3000), new BigDecimal(2000), "1111 2222 3333 4444", "Tomike", 123));
// 			add(new Payment(new BigDecimal(4000), new BigDecimal(2000), "1141 2922 3338 4744", "Kwame", 178));

// 		}
// 	};

// 	private URI baseURI;

// 	@Autowired
// 	private ListPaymentService listPaymentService;

// 	@BeforeEach
// 	public void setUp() throws Exception {
// 		this.baseURI = UriComponentsBuilder.newInstance()
// 				.scheme("http")
// 				.host("localhost")
// 				.port(port)
// 				.path("api/payment")
// 				.build()
// 				.toUri();

// 		for (Iterator<Payment> it = listPaymentService.getAllPayments().iterator(); it.hasNext();) {
// 			it.next();
// 			it.remove();
// 		}

// 		for (Payment payments : defaultPayments) {
// 			listPaymentService.createPayment(payments);
// 		}
// 	}
// }