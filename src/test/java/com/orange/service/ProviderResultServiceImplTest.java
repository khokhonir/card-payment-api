package com.orange.service;

import com.orange.Application;
import com.orange.model.ProviderResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest(classes = Application.class)
public class ProviderResultServiceImplTest {

    @Autowired
    ProviderResultServiceImpl providerResultServiceImpl;

    @Test
    void getProviderResults(){

        List<ProviderResult> providerResultsList = providerResultServiceImpl.getProviderResults();

        assertEquals( providerResultsList.stream().mapToInt(x -> x.getNumberOfTransactions()).findFirst().getAsInt(), 300);

    }

}
