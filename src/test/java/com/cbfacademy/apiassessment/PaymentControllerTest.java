package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.ListPaymentService;
import com.cbfacademy.apiassessment.Payment;
import com.cbfacademy.apiassessment.PaymentController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentControllerTest {

    private PaymentController paymentController;
    private ListPaymentService listPaymentService;

    @BeforeEach
    void setUp() {
        listPaymentService = new ListPaymentService(); // You might want to create a test-specific implementation
        paymentController = new PaymentController(listPaymentService);
    }

    @Test
    void testGetAllPayments() {
        ResponseEntity<List<Payment>> responseEntity = paymentController.getAllPayments();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<Payment> payments = responseEntity.getBody();
        assertEquals(1, payments.size());
        assertEquals("Kwame", payments.get(0).getCardHolderName());
    }

    @Test
    void testCreatePayment() {
        Payment newPayment = new Payment(new BigDecimal(200), new BigDecimal(100), "9876 5432 1098 7654", "Jane Doe", 456);

        ResponseEntity<Payment> responseEntity = paymentController.createPayment(newPayment);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        Payment createdPayment = responseEntity.getBody();
        assertEquals("Jane Doe", createdPayment.getCardHolderName());
    }



}