package com.orange.service;

import com.orange.model.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HoutPayServiceImpl implements PaymentProcessorService {

    private static final double  feePerc1 = 0.035;
    private static final double  feePerc2 = 0.02;
    private double providerFee = 0.00;


    @Override
    public double calculateTransactionFee(Transaction transaction) {

        providerFee = 0.00;

        if (transaction.getCountry().getName().equals(Country.southAfrica().getName()) && transaction.getCardType().equals(CardType.HoutPay)) {
            providerFee = transaction.getTransactionAmount() * feePerc2;

        }

        if (!transaction.getCountry().getName().equals(Country.southAfrica().getName()) && !transaction.getCardType().equals(CardType.HoutPay)) {
            providerFee = transaction.getTransactionAmount() * feePerc1;

        }

        log.info("Transaction Amount : {} , Transaction Country : {} , Provider Name : {}, Provider Fee : {} " , transaction.getTransactionAmount(),transaction.getCountry().getName(), Provider.HoutPay ,providerFee);

        return providerFee;
    }

}
