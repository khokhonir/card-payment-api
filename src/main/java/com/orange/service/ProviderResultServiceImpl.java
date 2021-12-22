package com.orange.service;

import com.orange.model.ProviderResult;
import com.orange.model.Provider;
import com.orange.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderResultServiceImpl implements ProviderResultService {


    @Autowired
    HoutPayServiceImpl surePay;

    @Autowired
    QuickPayServiceImpl quickPay;

    @Autowired
    PayMastersServiceImpl payMasters;

    @Autowired
    HoutPayServiceImpl houtPay;

    @Autowired
    TransactionService transactionService;


    @Autowired
    OrangePaymentService orangePaymentService;

    List<ProviderResult> providerResultsList;

    @Override
    public List<ProviderResult> getProviderResults() {


        List<ProviderResult> providerResultsList = new ArrayList<ProviderResult>();
        double surePayFee = 0.00;
        Integer  surePayNumberOfTransactions = 0;

        double quickPayFee = 0.00;
        Integer  quickPayNumberOfTransactions = 0;

        double mastersPayFee = 0.00;
        Integer  mastersPayNumberOfTransactions = 0;

        double houtPayFee = 0.00;
        Integer  houtPayNumberOfTransactions = 0;

        for ( Transaction transaction : transactionService.getTransactions()) {

            surePayFee  += surePay.calculateFee(transaction);
            surePayNumberOfTransactions   += surePay.getNumberOfTransactions(transaction);
            quickPayFee += quickPay.calculateFee(transaction);
            quickPayNumberOfTransactions  += quickPay.getNumberOfTransactions(transaction);
            mastersPayFee += payMasters.calculateFee(transaction);
            mastersPayNumberOfTransactions  += payMasters.getNumberOfTransactions(transaction);
            houtPayFee += houtPay.calculateFee(transaction);
            houtPayNumberOfTransactions  += houtPay.getNumberOfTransactions(transaction);

        }

        providerResultsList.add(new ProviderResult(Provider.SurePay,surePayFee,surePayNumberOfTransactions));
        providerResultsList.add(new ProviderResult(Provider.QuickPay,quickPayFee,quickPayNumberOfTransactions));
        providerResultsList.add(new ProviderResult(Provider.PayMasters,mastersPayFee,mastersPayNumberOfTransactions));
        providerResultsList.add(new ProviderResult(Provider.HoutPay,houtPayFee,houtPayNumberOfTransactions));

        return providerResultsList;
    }
}
