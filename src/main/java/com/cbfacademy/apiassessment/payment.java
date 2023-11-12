package com.cbfacademy.apiassessment;

import java.math.BigDecimal;
import java.util.UUID;

public class Payment {

    private final UUID id;;
    private BigDecimal amount;
    private BigDecimal cardBalance;
    private String cardNumber;
    private String cardholderName;
    // private double expirationDate;
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

    public BigDecimal getAmount(BigDecimal amount) {
        return this.amount;
    }

    public BigDecimal getBalance(BigDecimal cardBalance) {
        return this.cardBalance;
    }

    public void setcardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getcardNumber(String cardNumber) {
        return this.cardNumber;
    }

    public void setcardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getcardholderName(String cardholderName) {
        return this.cardholderName;
    }

    // public void setexpirDate(double expirationDate) {
    //     this.expirationDate = expirationDate;
    // }

    // public double getexpirDate(double expirationDate) {
    //     return this.expirationDate;
    // }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getCvv(int cvv) {
        return this.cvv;
    }
}
