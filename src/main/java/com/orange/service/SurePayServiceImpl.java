package com.orange.service;

import com.orange.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SurePayServiceImpl implements PaymentProviderService {
    private static final Provider name = Provider.SurePay;
    private static final double flatRate = 0.04;
    private static final double feePerc1 = 0.03;
    private static final double feePerc2 = 0.025;
    private double fee = 0.00;
    private int totalTransactionValue = 0;
    private double totalFee = 0;

    @Autowired
    ConvertTransactionFeeServiceImplToRands convertTransactionFeeServiceImplToRands;

    @Override
    public double calculateFee(Transaction transaction) {
        if (!transaction.getCountry().equals(Country.india())) {

            if (transaction.getCountry().equals(Country.usa())) {

                if (transaction.getTransactionAmount() <= 500 && !transaction.getCardType().equals(CardType.HoutPay)) {
                    fee = transaction.getTransactionAmount() * feePerc1;

                }
                if (transaction.getTransactionAmount() > 500 && !transaction.getCardType().equals(CardType.HoutPay)) {
                    fee = transaction.getTransactionAmount() * feePerc2;

                }

                //Convert the fee to rands
                log.debug("1(a)Transaction before conversion : Provider {}, Country {}, Fee {} " , Provider.SurePay.name() , transaction.getCountry() ,transaction.getTransactionAmount());
                fee = convertTransactionFeeServiceImplToRands.convertCurrency(transaction.getCountry().getCountryCode(), fee);
                log.debug("1(b)Transaction after conversion : Provider {}, Country {}, Fee {} " , Provider.SurePay.name() , transaction.getCountry() ,transaction.getTransactionAmount());
                totalFee += fee;

            }

            if (transaction.getCardType().equals(CardType.HoutPay) && !transaction.getCountry().equals(Country.usa())) {

                log.debug("1(c)Transaction before conversion : Provider {}, Country {}, Fee {} " , Provider.SurePay.name() , transaction.getCountry() ,transaction.getTransactionAmount());
                fee = transaction.getTransactionAmount()  * flatRate;

                //Convert the fee to rands
                fee = convertTransactionFeeServiceImplToRands.convertCurrency(transaction.getCountry().getCountryCode(), fee);
                log.debug("1(d)Transaction after conversion : Provider {}, Country {}, Fee {} " , Provider.SurePay.name() , transaction.getCountry() ,transaction.getTransactionAmount());
                totalFee = totalFee + fee;
            }

        }

        log.debug("1(e)Fee Total : Provider {}, Total Fee {} " , Provider.SurePay.name() ,totalFee);

        return totalFee;
    }


    @Override
    public int getNumberOfTransactions(Transaction transaction) {
        calculateFee(transaction);
        if (calculateFee(transaction) > 0) {
            totalTransactionValue += 1;
            log.debug("1(F) Transactions Total : Provider {},  Transactions Total {} " , Provider.SurePay.name() , totalTransactionValue);

        }
        return totalTransactionValue;
    }
}
