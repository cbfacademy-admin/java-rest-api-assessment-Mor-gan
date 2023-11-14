package com.cbfacademy.apiassessment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final ListPaymentService listPaymentService;

    public PaymentController(ListPaymentService listPaymentService) {
        this.listPaymentService = listPaymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = listPaymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payments) {
        Payment createdNewPayment = listPaymentService.createPayment(payments);
        return new ResponseEntity<>(createdNewPayment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable UUID id, @RequestBody Payment payments) {
        Payment updatedpayment = listPaymentService.updatePayment(id, payments);

        if (updatedpayment != null) {
            return new ResponseEntity<>(updatedpayment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/details")
    public ResponseEntity<List<String>> getPaymentDetails(@RequestParam UUID id) {
        List<Payment> payments = listPaymentService.getAllPayments();

        List<String> paymentDetails = new ArrayList<>();

        for (Payment payment : payments) {
            if (payment.getId().equals(id)) {
                paymentDetails.add("Amount: " + payment.getAmount(null) + ", Payer: " + payment.getCardHolderName());
            }
        }

        if (!paymentDetails.isEmpty()) {
            return new ResponseEntity<>(paymentDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
