package com.cbfacademy.apiassessment;

import com.cbfacademy.filehandler.InsufficientBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

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
        assertEquals(3, payments.size());
    }

    @Test
    void testCreatePayment() throws InsufficientBalanceException {
        Payment newPayment = new Payment(new BigDecimal(200), new BigDecimal(7850), "9876 5432 1098 7654", "Jane Doe",
                456);

        ResponseEntity<Payment> responseEntity = paymentController.processPayment(newPayment);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        Payment createdPayment = responseEntity.getBody();
        assertEquals("Jane Doe", createdPayment.getCardHolderName());
    }

}
