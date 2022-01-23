package com.orange.controller;

import java.util.List;
import com.orange.model.*;
import com.orange.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
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

}

