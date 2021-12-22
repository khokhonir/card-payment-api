package com.orange.service;

import com.orange.model.Transaction;

public interface ConvertTransactionFeeService {

    public double convertCurrency(String country, double fee);

}



