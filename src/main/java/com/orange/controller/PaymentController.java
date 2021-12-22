package com.orange.controller;

import com.orange.model.OrangePayment;
import com.orange.model.ProviderResult;
import com.orange.service.OrangePaymentService;
import com.orange.service.ProviderResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
public class PaymentController {

    @Autowired
    OrangePaymentService orangePaymentService;

    OrangePayment orangePayment;
    ModelAndView model = new ModelAndView();

    @GetMapping("/getpayments")
    public ModelAndView providerList() {


        orangePayment = orangePaymentService.setOrangePaymentResults();
        model.addObject("revenue", orangePayment.getGeneratedRevenue());
        model.addObject("transactionsTotal", "320");
        model.addObject("providersFees", orangePayment.getCashPaidToProviders());
        model.setViewName("payment_list");
        return model;
    }


}

