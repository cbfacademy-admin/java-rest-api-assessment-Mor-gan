package com.cbfacademy.apiassessment;

import com.cbfacademy.filehandler.InsufficientBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// import static org.mockito.Mockito.when;
import java.util.UUID;
// import java.net.URI;

public class PaymentControllerTest {

    private PaymentController paymentController;
    private ListPaymentService listPaymentService;
    // private URI baseURI;

    @BeforeEach
    void setUp() {
        listPaymentService = new ListPaymentService(); // to create a test-specific implementation
        paymentController = new PaymentController(listPaymentService);
    }

    @Test
    void testGetAllPayments() {
        ResponseEntity<List<Payment>> responseEntity = paymentController.getAllPayments();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<Payment> payments = responseEntity.getBody();
        assertEquals(3, payments.size()); // add other asserts
    }

    @Test
    void testCreatePayment() throws InsufficientBalanceException {
        Payment newPayment = new Payment(new BigDecimal(200), new BigDecimal(670), "9876 5432 1098 7654", "Jane Doe",
                456);

        ResponseEntity<Payment> responseEntity = paymentController.processPayment(newPayment);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        Payment createdPayment = responseEntity.getBody();
        assertEquals("Jane Doe", createdPayment.getCardHolderName());
    }
@Test
    void testProcessPaymentWithSufficientBalance() {
  
        BigDecimal initialBalance = BigDecimal.valueOf(500.0);
        Payment createPayment = new Payment(BigDecimal.valueOf(50.0), initialBalance, null, null, 0);

        try {
            Payment result = listPaymentService.processPayment(createPayment);

            assertNotNull(result);
            assertEquals(createPayment, result);
            assertTrue(listPaymentService.getAllPayments().contains(result));
        } catch (InsufficientBalanceException e) {
            fail("Unexpected InsufficientBalanceException");
        }
    }

@Test
    void testProcessPaymentWithInsufficientBalance() {
    
        BigDecimal initialBalance = BigDecimal.valueOf(50.0);
        Payment createPayment = new Payment(BigDecimal.valueOf(100.0), initialBalance, null, null, 0);

        assertThrows(InsufficientBalanceException.class, () -> {
            listPaymentService.processPayment(createPayment);
        });
    }

    @Test
    void testCancelPaymentWithInvalidId() {
    
        UUID invalidId = UUID.randomUUID();

        boolean isCanceled = listPaymentService.cancelPayment(invalidId);

        assertFalse(isCanceled);
    }

}
