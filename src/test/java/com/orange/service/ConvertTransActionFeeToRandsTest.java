package com.orange.service;

import com.orange.Application;
import com.orange.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
public class ConvertTransActionFeeToRandsTest {

    @Autowired
    TransactionServiceImpl transactionServiceImpl;

    @Autowired
    ConvertTransactionFeeServiceImplToRands convertTransactionFeeImplToRands;

    double convertedFeeForUS = 0.00;
    double convertedFeeForIndia = 0.00;
    double convertedFeeForUK = 0.00;

    Transaction transactionIndia;
    Transaction transactionUK;
    Transaction transactionUS;


    @Test
    void convertTransActionFeeToRandsTest(){
        List<Transaction> transactionList = transactionServiceImpl.getTransactions();

        //India
        transactionIndia = transactionList.stream().filter(o -> o.getCountry().getCountryCode().equals("IN")).findFirst().get();

        //UK
        transactionUK= transactionList.stream().filter(o -> o.getCountry().getCountryCode().equals("UK")).findFirst().get();

        //US
        transactionUS = transactionList.stream().filter(o -> o.getCountry().getCountryCode().equals("US")).findFirst().get();


        convertedFeeForIndia = convertTransactionFeeImplToRands.convertCurrency(transactionIndia.getCountry().getCountryCode(),transactionIndia.getTransactionAmount());
        convertedFeeForUK = convertTransactionFeeImplToRands.convertCurrency(transactionUK.getCountry().getCountryCode(),transactionUK.getTransactionAmount());
        convertedFeeForUS = convertTransactionFeeImplToRands.convertCurrency(transactionUS.getCountry().getCountryCode(),transactionUS.getTransactionAmount());

        assertEquals( convertedFeeForIndia, 209.0);
        assertEquals( convertedFeeForUK, 10500.0);
        assertEquals( convertedFeeForUS, 79200.0);
        }


}
