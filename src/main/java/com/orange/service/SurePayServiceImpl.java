package com.orange.service;

import com.orange.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SurePayServiceImpl implements PaymentProcessorService {

    private static final double feePerc1 = 0.03;
    private static final double feePerc2 = 0.025;
    private static final double feePerc3 = 0.04;
    private double providerFee = 0.00;

    @Override
    public double calculateTransactionFee(Transaction transaction) {
        providerFee = 0.00;
        if (!transaction.getCountry().getName().equals(Country.india().getName())) {

                if (transaction.getTransactionAmount() <= 500 && !transaction.getCardType().equals(CardType.HoutPay)) {
                    providerFee = transaction.getTransactionAmount() * feePerc1;

                }
                if (transaction.getTransactionAmount() > 500 && !transaction.getCardType().equals(CardType.HoutPay)) {
                    providerFee = transaction.getTransactionAmount() * feePerc2;

                }
                if (transaction.getCardType().equals(CardType.HoutPay)) {
                    providerFee = transaction.getTransactionAmount() * feePerc3;

                }

        }

        log.info("Transaction Amount : {} , Transaction Country : {} , Provider Name : {}, Provider Fee : {} " , transaction.getTransactionAmount(),transaction.getCountry().getName(), Provider.SurePay ,providerFee);

        return providerFee;
    }

}
