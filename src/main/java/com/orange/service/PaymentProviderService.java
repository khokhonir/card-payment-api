package com.orange.service;

import com.orange.model.Transaction;

public interface PaymentProviderService {

    double calculateFee(Transaction transaction);
    int getNumberOfTransactions(Transaction transaction);

}
