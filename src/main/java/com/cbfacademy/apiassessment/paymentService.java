package com.cbfacademy.apiassessment;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    
    Payment getId(UUID id);

   List<Payment>getAllpayments(); 

   Payment createPayment(Payment createPayment);
   
   Payment updatePayment(UUID id, Payment updatePayment);

   Payment cancelPayment(UUID id, Payment cancelPayment);

} 