package com.orange.service;

import com.orange.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.NumberFormat;
import java.util.Locale;

@Slf4j
@Service
public class OrangePaymentServiceImpl implements OrangePaymentService {

    @Autowired
    private TransactionResultServiceImpl transactionResultServiceImpl;

    private OrangePayment orangePayment;
    private long totalValueOfAllTransactions = 0;
    private double generatedRevenue = 0.00;
    private Locale zarLocale;
    private NumberFormat zarFormat;


    @Override
    public OrangePayment setOrangePaymentResults() {

        orangePayment = new OrangePayment();

        double sumLeastProviderFee = transactionResultServiceImpl.getTransactionResults().stream().filter(o -> o.getStatus().equals(TransactionStatus.Success)).mapToDouble(TransactionResult::getTransactionFee).sum();

        totalValueOfAllTransactions  = transactionResultServiceImpl.getTransactionResults().stream().filter(o -> o.getStatus().equals(TransactionStatus.Success)).count();

        for( TransactionResult transactionResult : transactionResultServiceImpl.getTransactionResults()){
            if( transactionResult.getStatus() == TransactionStatus.Success){
               generatedRevenue = generatedRevenue +  transactionResult.getTransaction().getTransactionAmount();
            }
        }

        // Add the dollar sigh in the totals
        zarLocale = new Locale("EN","US");
        zarFormat = NumberFormat.getCurrencyInstance(zarLocale);
        generatedRevenue = generatedRevenue * 0.045;

        log.info("The formated generated revenue is : " + zarFormat.format(generatedRevenue));
        orangePayment.setTotalValueOfAllTransactions(String.format("%d",totalValueOfAllTransactions));
        orangePayment.setGeneratedRevenue(zarFormat.format(generatedRevenue)); //Orange flat fee for all card transactions
        orangePayment.setCashPaidToProviders(zarFormat.format(sumLeastProviderFee));

        return orangePayment;
    }
}
