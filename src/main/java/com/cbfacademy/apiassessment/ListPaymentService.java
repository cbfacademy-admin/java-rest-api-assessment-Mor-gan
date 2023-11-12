package com.cbfacademy.apiassessment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ListPaymentService implements PaymentService {

    private final List<Payment> payments = new ArrayList<>();

    @Override
    public List<Payment> getAllpayments() {
        return payments;
    }

    @Override
    public Payment createPayment(Payment createPayment) {
        payments.add(createPayment);
        return createPayment;
    }

    @Override
    public Payment updatePayment(UUID id, Payment updatePayment) {
        for(int i = 0; i < payments.size(); i++){
         Payment payment = payments.get(i);

         if(payment.getId().equals(id)){
          payments.set(i, updatePayment);
          return updatePayment;
         }
        }
        return null;
    }


    @Override
    public Payment cancelPayment(UUID id, Payment cancelPayment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelPayment'");
    }

    
}
