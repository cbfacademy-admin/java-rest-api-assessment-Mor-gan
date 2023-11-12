package com.cbfacademy.apiassessment;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ious")
public class PaymentController {
   
    private final ListPaymentService listPaymentService;

    public PaymentController(ListPaymentService listPaymentService){
        this.listPaymentService = listPaymentService;
    }

    @GetMapping

    public ResponseEntity <List<Payment>> getAllpayments(){
        List<Payment> payments = listPaymentService.getAllpayments();

        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}
