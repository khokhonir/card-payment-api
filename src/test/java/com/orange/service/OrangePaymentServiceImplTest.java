package com.orange.service;

import com.orange.Application;
import com.orange.model.OrangePayment;
import com.orange.model.ProviderResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
//@ActiveProfiles("test")
public class OrangePaymentServiceImplTest {

    @Autowired
    ProviderResultServiceImpl providerResultServiceImpl;

    @Autowired
    OrangePaymentServiceImpl orangePaymentServiceImpl;

    @Test
    void getOrangePayments(){

        OrangePayment orangePayment = orangePaymentServiceImpl.setOrangePaymentResults();
        assertEquals( orangePayment.getTotalValueOfAllTransactions(), "320");

        }



}
