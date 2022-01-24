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
    void calculateTransaction1(){

        Transaction transaction = new Transaction(1l, CardType.HoutPay, Country.southAfrica(), 300);

        double fee = houtPayServiceImpl.calculateTransactionFee(transaction);

        assertEquals( fee, 6);

    }

    @Test
    void calculateTransaction2(){

        Transaction transaction = new Transaction(1l, CardType.MasterCard, Country.india(), 1000);

        double fee = houtPayServiceImpl.calculateTransactionFee(transaction);

        assertEquals( fee, 35);

    }

    @Test
    void calculateTransaction3(){

        Transaction transaction = new Transaction(1l, CardType.Visa, Country.india(), 500);

        double fee = houtPayServiceImpl.calculateTransactionFee(transaction);

        assertEquals( fee, 17.5);

    }

    @Test
    void calculateTransaction4(){

        Transaction transaction = new Transaction(1l, CardType.MasterCard, Country.houtBay(), 1399);

        double fee = houtPayServiceImpl.calculateTransactionFee(transaction);

        assertEquals( fee, 48.965);

    }

    @Test
    void calculateTransaction5(){

        Transaction transaction = new Transaction(1l, CardType.DinersClub, Country.uk(), 4000);

        double fee = houtPayServiceImpl.calculateTransactionFee(transaction);

        assertEquals( fee, 140.0);

    }





}
