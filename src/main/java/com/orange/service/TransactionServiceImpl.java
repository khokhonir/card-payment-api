package com.orange.service;

import com.orange.model.CardType;
import com.orange.model.Country;
import com.orange.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    List<Transaction> transactions = new ArrayList<Transaction>();

    {
        transactions.add(new Transaction(1L, CardType.MasterCard, Country.india(), 1000));
        transactions.add(new Transaction(1L, CardType.HoutPay, Country.southAfrica(), 300));
        transactions.add(new Transaction(1L, CardType.Visa, Country.uk(), 500));
        transactions.add(new Transaction(1L, CardType.DinersClub, Country.usa(), 5000));
        transactions.add(new Transaction(1L, CardType.Visa, Country.india(), 300));
        transactions.add(new Transaction(1L, CardType.HoutPay, Country.usa(), 4500));
        transactions.add(new Transaction(1L, CardType.MasterCard, Country.houtBay(), 1399));
        transactions.add(new Transaction(1L, CardType.DinersClub, Country.uk(), 4000));
        transactions.add(new Transaction(1L, CardType.MasterCard, Country.southAfrica(), 3500));
        transactions.add(new Transaction(1L, CardType.DinersClub, Country.southAfrica(), 1500));

    }

    @Override
    public List<Transaction> getTransactions() {
       return transactions;
    }


}
