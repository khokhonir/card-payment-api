package com.orange.service;

import com.orange.Application;
import com.orange.model.TransactionResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
public class ProviderResultServiceImplTest {

    @Autowired
    TransactionResultServiceImpl transactionResultServiceImpl;

    @Test
    void calculateFee(){


        List<TransactionResult> transactionResult = transactionResultServiceImpl.getTransactionResults();

        assertEquals( transactionResult.size(), 27);

    }




}
