package com.orange.service;

import com.orange.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PayMastersServiceImpl implements PaymentProviderService {
    private static final Provider name = Provider.PayMasters;
    private static final double feePerc = 0.03;
    private double fee = 0.00;
    private int totalTransactionValue = 0;
    private double totalFee = 0;

    @Autowired
    ConvertTransactionFeeServiceImplToRands convertTransactionFeeServiceImplToRands;


    @Override
    public double calculateFee(Transaction transaction) {



        if (transaction.getCardType().equals(CardType.DinersClub) || transaction.getCardType().equals(CardType.MasterCard) ) {
            //Convert the fee to rands
            log.debug("3(a)Transaction before conversion : Provider {}, Country {}, Fee {} " , Provider.PayMasters.name() , transaction.getCountry() ,transaction.getTransactionAmount());
            fee = convertTransactionFeeServiceImplToRands.convertCurrency(transaction.getCountry().getCountryCode(), fee);
            log.debug("3(b)Transaction after conversion : Provider {}, Country {}, Fee {} " , Provider.PayMasters.name() , transaction.getCountry() ,transaction.getTransactionAmount());

            fee =  transaction.getTransactionAmount() * feePerc ;
            totalFee += fee;
        }

        log.debug("3(c) Fee Total : Provider {}, Total Fee {} " , Provider.PayMasters.name() ,totalFee);

        return fee;
    }


    @Override
    public int getNumberOfTransactions(Transaction transaction) {
        calculateFee(transaction);
        if (calculateFee(transaction) > 0) {
            totalTransactionValue += 1;
            log.debug("3(d) Transactions Total : Provider {},  Transactions Total {} " , Provider.PayMasters.name() , totalTransactionValue);

        }
        return totalTransactionValue;
    }
}
