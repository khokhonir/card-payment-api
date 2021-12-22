package com.orange.service;

import org.springframework.stereotype.Service;

@Service
public class ConvertTransactionFeeServiceImplToRands implements ConvertTransactionFeeService {


    private Double transactionFeeInRands;

    @Override
    public double convertCurrency(String country, double fee) {

        switch (country) {
            case ("US"): // dollar (symbol: $)
                transactionFeeInRands = fee * 15.84;
                break;
            case ("UK"): // pound sterling (symbol: £)
                transactionFeeInRands = fee * 21.00;
                break;
            case ("IN"): // Indian rupee (symbol:  ₹)
                transactionFeeInRands = fee* 0.209;
                break;

        }
        return transactionFeeInRands;
    }


}
