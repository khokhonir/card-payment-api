package com.orange.service;
import com.orange.model.Transaction;

import java.util.List;


public interface TransactionService {

    List<Transaction> getTransactions();
}