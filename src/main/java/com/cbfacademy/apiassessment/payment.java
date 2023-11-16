package com.cbfacademy.apiassessment;

import java.math.BigDecimal;
import java.util.UUID;

public class Payment {

    private final UUID id;
    private BigDecimal amount;
    private BigDecimal cardBalance;
    private String cardNumber;
    private String cardholderName;
    private int cvv;

    public Payment(BigDecimal amount, BigDecimal cardBalance, String cardNumber, String cardholderName,
             int cvv) {

        this.id = UUID.randomUUID();
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.cardBalance = cardBalance;
        this.cardholderName = cardholderName;
        this.cvv = cvv;
    }

    public UUID getId() {
        return this.id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber(String cardNumber) {
        return this.cardNumber;
    }

    public void setCardHolderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCardHolderName() {
        return this.cardholderName;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getCvv(int cvv) {
        return this.cvv;
    }

    public BigDecimal getAmount() {
        return this.amount
        ;
    }

    public BigDecimal getBalance() {
        return this.cardBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
    }
}
