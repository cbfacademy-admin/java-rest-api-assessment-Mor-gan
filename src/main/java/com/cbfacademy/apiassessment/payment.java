package com.cbfacademy.apiassessment;

import java.math.BigDecimal;

public class payment {
    
    private String customerId;
    private BigDecimal amount;
    private double cardNumber;
    private String cardholderName;
    private double expirationDate;
    private int cvv;

 public payment(String customerId, BigDecimal amount, double cardNumber, String cardholderName,double expirationDate, int cvv ){
    this.customerId = customerId;
    this.amount =amount;
    this.cardNumber = cardNumber;
    this.cardholderName=cardholderName;
    this.expirationDate =expirationDate;
    this.cvv = cvv;
 }

public String getID(String customerId){
    return this.customerId;
}
public void setAmount(BigDecimal amount){
   this.amount = amount;
}
public BigDecimal getAmount(BigDecimal amount){
    return this.amount;
}
public void setcardNumber(double cardNumber){
     this.cardNumber= cardNumber;
}
public double getcardNumber(double cardNumber){
    return this.cardNumber;
}
public void setcardholderName(String cardholderName){
     this.cardholderName = cardholderName;
}
public String getcardholderName(String cardholderName){
    return this.cardholderName;
}
public void setexpirDate(double expirationDate){
     this.expirationDate = expirationDate;
}
public double getexpirDate(double expirationDate){
    return this.expirationDate;
}
public void setCvv(int cvv){
     this.cvv= cvv;
}
public int getCvv(int cvv){
    return this.cvv;
}
}
