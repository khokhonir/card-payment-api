package com.orange.service;

import com.orange.model.OrangePayment;
import com.orange.model.ProviderResult;
import com.orange.model.TransactionResult;

import java.util.List;

public interface TransactionResultService {

    List<TransactionResult>  getTransactionResults();
}
