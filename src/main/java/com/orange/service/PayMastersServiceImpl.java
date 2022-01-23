package com.orange.service;

import com.orange.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PayMastersServiceImpl implements PaymentProcessorService {

    private static final double feePerc = 0.03;
    private double providerFee = 0.00;

    @Override
    public double calculateTransactionFee(Transaction transaction) {
        providerFee = 0.00;

        if (transaction.getCardType().equals(CardType.Visa) || transaction.getCardType().equals(CardType.MasterCard) ) {

            providerFee =  transaction.getTransactionAmount() * feePerc ;

        }

        log.info("Transaction Amount : {} , Transaction Country : {} , Provider Name : {}, Provider Fee : {} " , transaction.getTransactionAmount(),transaction.getCountry().getName(), Provider.PayMasters ,providerFee);

        return providerFee;
    }

}
