package com.orange.controller;
import java.util.List;

import com.orange.model.*;
import com.orange.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    ModelAndView model = new ModelAndView();

    @GetMapping("/gettransactions")
    public ModelAndView transactionList() {

        List<Transaction> transactionList = transactionService.getTransactions();
        model.addObject("transactionLists", transactionList);
        model.setViewName("transaction_list");
        return model;
    }

    //Postman testing
    @GetMapping(value = "/transactions")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }



}

