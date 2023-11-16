package com.cbfacademy.apiassessment;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cbfacademy.filehandler.InsufficientBalanceException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ListPaymentService extends PaymentService {

    private static final String outputFile = "/Users/bimbo/Desktop/cbfacademy/java-rest-api-assessment-Mor-gan/src/main/resources/paymentFile.json";
    private final List<Payment> payments;
    ObjectMapper objectMapper = new ObjectMapper();

    public ListPaymentService() {
        // Initialze all the instance variable inside a constructor
        this.payments = new ArrayList<>();
        Payment firstPayment = new Payment(new BigDecimal(2000), new BigDecimal(4000), "1141 2922 3338 4744", "Kwame",
                178);
        Payment secondPayment = new Payment(new BigDecimal(3000), new BigDecimal(3500), "2233 4455 6677 8899", "Alice",
                279);
        Payment thirdPayment = new Payment(new BigDecimal(5000), new BigDecimal(7500), "9876 5432 1098 7654", "Bob",
                345);
        payments.add(firstPayment);
        payments.add(secondPayment);
        payments.add(thirdPayment);

        if (!payments.isEmpty()) {
            // Payment paymenttosave = payments.get(0);
            try {
                File outputFileObj = Paths.get(outputFile).toFile();
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(outputFileObj, payments);
                System.out.println("File created at: " + outputFile);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("File Not created");
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        return payments;
    }

    @Override
    public Payment createPayment(Payment createPayment) {
        try {
            BigDecimal paymentAmount = createPayment.getAmount();
            BigDecimal cardBalance = createPayment.getBalance();

            // Check if the card balance is sufficient for the payment
            if (cardBalance.compareTo(paymentAmount) >= 0) {
                // Deduct the payment amount from the balance
                BigDecimal newBalance = cardBalance.subtract(paymentAmount);
                createPayment.setNewBalance(newBalance);
                // Add the payment to the list
                payments.add(createPayment);
                return createPayment; // Return the created payment on success
            } else {
                throw new InsufficientBalanceException("Insufficient balance for payment");
            }
        } catch (InsufficientBalanceException e) {
            System.err.println(e.getMessage());
            return null; // Return null or handle the failure case accordingly
        }
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
    public boolean cancelPayment(UUID id) {
        return payments.removeIf(delpayment -> delpayment.getId().equals(id));
    }

}
