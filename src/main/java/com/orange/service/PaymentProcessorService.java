package com.orange.service;

import com.orange.model.Transaction;

public interface PaymentProcessorService {

    double calculateTransactionFee(Transaction transaction);
}
