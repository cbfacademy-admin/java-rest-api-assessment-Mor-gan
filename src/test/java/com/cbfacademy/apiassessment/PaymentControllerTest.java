package com.cbfacademy.apiassessment;

import com.cbfacademy.filehandler.InsufficientBalanceException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
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
        assertEquals(3, payments.size());
        assertEquals("Kwame", payments.get(0).getCardHolderName());
    }

    @Test
    void testCreatePayment() throws InsufficientBalanceException {
        Payment newPayment = new Payment(new BigDecimal(200), new BigDecimal(600), "9876 5432 1098 7654", "Jane Doe",
                456);

        ResponseEntity<Payment> responseEntity = paymentController.createPayment(newPayment);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        Payment createdPayment = responseEntity.getBody();
        assertEquals("Jane Doe", createdPayment.getCardHolderName());
    }

    @Test
    public void testCancelPayment() {
        ListPaymentService listPaymentService = new ListPaymentService();
        Payment paymentToDelete = new Payment(new BigDecimal(1000), new BigDecimal(500), "1234 5678 9012 3456",
                "John Doe", 123);
        listPaymentService.createPayment(paymentToDelete);

        // Get the ID of the payment to be canceled
        UUID paymentIdToDelete = paymentToDelete.getId();

        // Cancel the payment
        boolean paymentCanceled = listPaymentService.cancelPayment(paymentIdToDelete);

        // Check if the payment was successfully canceled
        Assertions.assertThat(paymentCanceled);
    }
}