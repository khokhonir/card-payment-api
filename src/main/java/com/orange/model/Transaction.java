package com.orange.model;

@lombok.Getter
@lombok.Setter
@lombok.ToString
public class Transaction {

    private Long customerId;
    private CardType cardType;
    private Country country;
    private double transactionAmount;

    public Transaction(long customerId, CardType cardType, Country country, double transactionAmount) {
        this.customerId = customerId;
        this.cardType = cardType;
        this.country = country;
        this.transactionAmount = transactionAmount;
    }

}
