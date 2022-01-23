package com.orange.controller;

import com.orange.model.TransactionResult;
import com.orange.service.TransactionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
//mark class as Controller

@Controller
@RestController
public class TransactionResultController {

    @Autowired
    TransactionResultService transactionResultsService;

    ModelAndView model = new ModelAndView();

    @GetMapping("/getorders")
    public ModelAndView transactionResultsList() {
        List<TransactionResult> transactionResultsList = transactionResultsService.getTransactionResults();
        model.addObject("transactionResultsLists", transactionResultsList);
        model.setViewName("transactionresult_list");
        return model;
    }

    //Postman testing
    @GetMapping(value = "/orders")
    public List<TransactionResult> getProviderResults() {
        return transactionResultsService.getTransactionResults();
    }

}

