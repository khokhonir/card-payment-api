package com.orange.service;

import com.orange.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HoutPayServiceImpl implements PaymentProviderService {
    private static final Provider name = Provider.HoutPay;
    private static final double  feePerc1= 0.035;
    private  double fee1 = 31.67; //2$ converted to rands
    private int totalTransactionValue = 0;
    private  double fee = 0;
    private double totalFee = 0;

    @Autowired
    ConvertTransactionFeeServiceImplToRands convertTransactionFeeServiceImplToRands;

    @Override
    public double calculateFee(Transaction transaction) {
        //Convert the fee to Rands
        log.debug("4(a) Transaction before conversion : Provider {}, Country {}, Fee {} " , Provider.HoutPay.name() , transaction.getCountry() ,transaction.getTransactionAmount());
        fee = convertTransactionFeeServiceImplToRands.convertCurrency(transaction.getCountry().getCountryCode(), fee);
        log.debug("4(b) Transaction   after conversion : Provider {}, Country {}, Fee {} " , Provider.HoutPay.name() , transaction.getCountry() ,transaction.getTransactionAmount());

        if (transaction.getCountry().equals(Country.southAfrica()) && transaction.getCardType().equals(CardType.HoutPay)) {

            log.debug("4(c)Transaction 2 after conversion : Provider {}, Country {}, Fee {} " , Provider.HoutPay.name() , transaction.getCountry() ,transaction.getTransactionAmount());
            totalFee = totalFee + fee1;
        }

        if (!transaction.getCountry().equals(Country.southAfrica()) && !transaction.getCardType().equals(CardType.HoutPay)) {

            fee = transaction.getTransactionAmount() * feePerc1;
            log.debug("4(d)Transaction 2 after conversion : Provider {}, Country {}, Fee {} " , Provider.HoutPay.name() , transaction.getCountry() ,transaction.getTransactionAmount());
            totalFee = totalFee + fee;
        }

        log.debug("4(e) Fee Total : Provider {}, Total Fee {} ", Provider.HoutPay.name(), totalFee);

        return fee;
    }

    @Override
    public int getNumberOfTransactions(Transaction transaction) {
        calculateFee(transaction);
        if (calculateFee(transaction) > 0) {
            totalTransactionValue += 1;
            log.debug("4(f) Transactions Total : Provider {},  Transactions Total {} " , Provider.HoutPay.name() , totalTransactionValue);

        }
        return totalTransactionValue;
    }
}
