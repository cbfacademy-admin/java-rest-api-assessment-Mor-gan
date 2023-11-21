package com.cbfacademy.apiassessment;


import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbfacademy.filehandler.InsufficientBalanceException;

@RestController
@RequestMapping("v1/api")
public class PaymentController {

    private final ListPaymentService listPaymentService;

    public PaymentController(ListPaymentService listPaymentService) {
        this.listPaymentService = listPaymentService;
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = listPaymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @PostMapping("/processpayment")
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payments) throws InsufficientBalanceException {
        Payment createdNewPayment = listPaymentService.processPayment(payments);
        return new ResponseEntity<>(createdNewPayment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable("id") UUID id, @RequestBody Payment payments) {
        Payment updatedpayment = listPaymentService.updatePayment(id, payments);

        if (updatedpayment != null) {
            return new ResponseEntity<>(updatedpayment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Payment> cancelPayment(@PathVariable("id") UUID id) {
        boolean canceled = listPaymentService.cancelPayment(id);

        if (canceled) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
