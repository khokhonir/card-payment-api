package com.orange.model;

@lombok.Getter
@lombok.Setter
public class TransactionResult {
    private final TransactionStatus status;
    private final double transactionFee;
    private final Provider provider;
    private final Transaction transaction;

    public TransactionResult(TransactionStatus status, double transactionFee, Provider provider, Transaction transaction) {
        this.status = status;
        this.transactionFee = transactionFee;
        this.provider = provider;
        this.transaction = transaction;
    }

}
