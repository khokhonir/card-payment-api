package com.orange.controller;

import com.orange.model.OrangePayment;
import com.orange.model.ProviderResult;
import com.orange.service.ProviderResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
//mark class as Controller

@Controller
@RestController
public class ProviderController {

    @Autowired
    ProviderResultService providerResultsService;

    ModelAndView model = new ModelAndView();

    @GetMapping("/getproviders")
    public ModelAndView providerList() {
        List<ProviderResult> providerResultsList = providerResultsService.getProviderResults();
        model.addObject("providerLists", providerResultsList);
        model.setViewName("provider_list");
        return model;
    }

    //Postman testing
    @GetMapping(value = "/providers")
    public List<ProviderResult> getProviderResults() {
        return providerResultsService.getProviderResults();
    }

}

