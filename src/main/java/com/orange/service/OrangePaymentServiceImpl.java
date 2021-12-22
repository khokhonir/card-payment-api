package com.orange.service;

import com.orange.model.OrangePayment;
import com.orange.model.ProviderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class OrangePaymentServiceImpl implements OrangePaymentService {

    @Autowired
    ProviderResultServiceImpl providerResultServiceImpl;

    @Override
    public OrangePayment setOrangePaymentResults() {

        OrangePayment orangePayment = new OrangePayment();

        int totalValueOfAllTransactions = 0;
        double generatedRevenue = 0.00;
        double revenuePaidToProviders = 0.00;

        final Locale zarLocale;
        final NumberFormat zarFormat;


        List<ProviderResult> providerResultsList = providerResultServiceImpl.getProviderResults();
        totalValueOfAllTransactions  = providerResultsList.stream().mapToInt(x -> x.getNumberOfTransactions()).sum();
        generatedRevenue = providerResultsList.stream().filter(o -> o.getTotalFee() > 0).mapToDouble(ProviderResult::getTotalFee).sum();

        // ZAR
        zarLocale = new Locale("en","ZA");
        zarFormat = NumberFormat.getCurrencyInstance(zarLocale);

        //Orange flat fee for all card transactions
        revenuePaidToProviders  = generatedRevenue;
        generatedRevenue = generatedRevenue * 0.045;


        log.debug("The formated generated revenue is : " + zarFormat.format(generatedRevenue));

        orangePayment.setTotalValueOfAllTransactions(String.format("%d",totalValueOfAllTransactions));
        orangePayment.setGeneratedRevenue(zarFormat.format(generatedRevenue)); //Orange flat fee for all card transactions
        orangePayment.setCashPaidToProviders(zarFormat.format(revenuePaidToProviders));

        return orangePayment;
    }
}
