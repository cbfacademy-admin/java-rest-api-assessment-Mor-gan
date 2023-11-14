package com.cbfacademy.apiassessment;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ListPaymentService implements PaymentService {

    private static final String outputFile = "/Users/bimbo/Desktop/cbfacademy/java-rest-api-assessment-Mor-gan/src/main/java/com/cbfacademy/apiassessment/paymentFile.json";
    private final List<Payment> payments;
    ObjectMapper objectMapper = new ObjectMapper();

    public ListPaymentService() {
        // Initialze all the instance variable inside a constructor
        this.payments = new ArrayList<>();
        Payment defaultPayment = new Payment(new BigDecimal(4000), new BigDecimal(2000), "1141 2922 3338 4744", "Kwame",
                178);
        payments.add(defaultPayment);

        if (payments.isEmpty()) {
            try {
                File outputFileObj = Paths.get(outputFile).toFile();
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(outputFileObj, payments);
                System.out.println("File created at: " + outputFile);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        return payments;
    }

    @Override
    public Payment createPayment(Payment createPayment) {
        payments.add(createPayment);
        return createPayment;
    }

    @Override
    public Payment updatePayment(UUID id, Payment updatePayment) {
        for (int i = 0; i < payments.size(); i++) {
            Payment payment = payments.get(i);

            if (payment.getId().equals(id)) {
                payments.set(i, updatePayment);// setting the index
                return updatePayment;
            }
            System.out.println("my payment update ");
        }
        return null;
    }

    @Override
    public Payment cancelPayment(UUID id, Payment cancelPayment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelPayment'");
    }
    // Try Java Streams

}
