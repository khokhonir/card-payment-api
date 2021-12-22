package com.orange.service;

import com.orange.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class QuickPayServiceImpl implements PaymentProviderService {
    private static final Provider name = Provider.QuickPay;
    private static final double flatRate = 0.03;
    private static final double feePerc1 = 0.025;
    private static final double feePerc2 = 0.04;
    private double fee = 0.00;
    private int totalTransactionValue = 0;
    private double totalFee = 0;

    @Autowired
    ConvertTransactionFeeServiceImplToRands convertTransactionFeeServiceImplToRands;


    @Override
    public double calculateFee(Transaction transaction) {

        if (!transaction.getCountry().equals(Country.southAfrica())){

            fee = transaction.getTransactionAmount();

            //Convert the fee to Rands
            log.debug("2(a) Transaction before conversion : Provider {}, Country {}, Fee {} " , Provider.QuickPay.name() , transaction.getCountry() ,transaction.getTransactionAmount());
            fee = convertTransactionFeeServiceImplToRands.convertCurrency(transaction.getCountry().getCountryCode(), fee);
            log.debug("2(b) Transaction   after conversion : Provider {}, Country {}, Fee {} " , Provider.QuickPay.name() , transaction.getCountry() ,transaction.getTransactionAmount());


            if (transaction.getCardType().equals(CardType.DinersClub) && !transaction.getCountry().equals(Country.india())) {
                fee =  transaction.getTransactionAmount() * feePerc1 * flatRate ;
                log.debug("2(c)Transaction 2 after conversion : Provider {}, Country {}, Fee {} " , Provider.QuickPay.name() , transaction.getCountry() ,transaction.getTransactionAmount());

                totalFee += fee;
            }
            if (transaction.getCardType().equals(CardType.DinersClub) && transaction.getCountry().equals(Country.india())) {
                fee =  transaction.getTransactionAmount() * feePerc2 * flatRate;
                log.debug("2(d) Transaction  3 after conversion : Provider {}, Country {}, Fee {} " , Provider.QuickPay.name() , transaction.getCountry() ,transaction.getTransactionAmount());

                totalFee += fee;
            }

            if (!transaction.getCardType().equals(CardType.DinersClub) && transaction.getCountry().equals(Country.india())) {
                fee =  transaction.getTransactionAmount() *  flatRate;
                log.debug("2(e) Transaction after conversion : Provider {}, Country {}, Fee {} " , Provider.QuickPay.name() , transaction.getCountry() ,transaction.getTransactionAmount());

                totalFee += fee;
            }

            if (!transaction.getCardType().equals(CardType.DinersClub) && !transaction.getCountry().equals(Country.india())) {
                fee =  transaction.getTransactionAmount() *  flatRate;
                log.debug("2(f) Transaction after conversion : Provider {}, Country {}, Fee {} " , Provider.QuickPay.name() , transaction.getCountry() ,transaction.getTransactionAmount());

                totalFee += fee;
            }
        }

        log.debug("2(g) Fee Total : Provider {}, Total Fee {} " , Provider.QuickPay.name() ,totalFee);

        return fee;
    }

    @Override
    public int getNumberOfTransactions(Transaction transaction) {
        calculateFee(transaction);
        if (calculateFee(transaction) > 0) {
            totalTransactionValue += 1;
            log.debug("2(F) Transactions Total : Provider {},  Transactions Total {} " , Provider.QuickPay.name() , totalTransactionValue);

        }
        return totalTransactionValue;
    }
}
