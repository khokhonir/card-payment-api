package com.orange.service;

import com.orange.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentProcessorServiceImpl implements PaymentProcessorService {


    @Override
    public double calculateTransactionFee(Transaction transaction) {
        return 0;
    }
}
