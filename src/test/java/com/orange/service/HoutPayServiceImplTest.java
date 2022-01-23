package com.orange.service;

import com.orange.Application;
import com.orange.model.CardType;
import com.orange.model.Country;
import com.orange.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
public class HoutPayServiceImplTest {

    @Autowired
    HoutPayServiceImpl houtPayServiceImpl;

    @Test
    void calculateFee(){

        Transaction transaction = new Transaction(1l, CardType.HoutPay, Country.southAfrica(), 300);

        double fee = houtPayServiceImpl.calculateTransactionFee(transaction);

        assertEquals( fee, 6);

    }




}
