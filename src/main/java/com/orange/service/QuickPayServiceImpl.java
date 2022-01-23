package com.orange.service;

import com.orange.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class QuickPayServiceImpl implements PaymentProcessorService {

    private static final double feePerc1 = 0.025;
    private static final double feePerc2 = 0.04;
    private static final double feePerc3 = 0.03;
    private double providerFee = 0.00;

    @Override
    public double calculateTransactionFee(Transaction transaction) {
        providerFee = 0.00;

        log.info("Transaction Amount : {} , Transaction Country : {} , Provider Name : {}, Provider Fee : {} " , transaction.getTransactionAmount(),transaction.getCountry().getName(), Provider.SurePay ,providerFee);


        if (!transaction.getCountry().getName().equals(Country.southAfrica().getName())) {

            if (transaction.getCardType().equals(CardType.DinersClub)) {

                providerFee = transaction.getTransactionAmount() * feePerc1;

                if (transaction.getCountry().getName().equals(Country.india().getName())) {
                    providerFee = transaction.getTransactionAmount() * feePerc2;
                }
            } else {
                providerFee = transaction.getTransactionAmount() * feePerc3;
            }

        }
        log.info("Transaction Amount : {} , Transaction Country : {} , Provider Name : {}, Provider Fee : {} " , transaction.getTransactionAmount(),transaction.getCountry().getName(), Provider.QuickPay ,providerFee);

        return providerFee;
    }

}

