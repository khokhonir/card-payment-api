package com.orange.service;

import com.orange.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransactionResultServiceImpl implements TransactionResultService {

    @Autowired
    SurePayServiceImpl surePay;

    @Autowired
    QuickPayServiceImpl quickPay;

    @Autowired
    PayMastersServiceImpl payMasters;

    @Autowired
    HoutPayServiceImpl houtPay;

    @Autowired
    TransactionService transactionService;

    @Autowired
    OrangePaymentService orangePaymentService;

    @Override
    public List<TransactionResult> getTransactionResults() {

        List<TransactionResult> transactionResultList = new ArrayList<TransactionResult>();
        List<Double> leastProviderFeesList = new ArrayList<Double>();
        List<TransactionResult> tempTransactionResultList = new ArrayList<TransactionResult>();
        double leastProviderFee  = 0.00;

        for (Transaction transaction : transactionService.getTransactions()) {

            tempTransactionResultList.add(new TransactionResult(TransactionStatus.Failed, surePay.calculateTransactionFee(transaction), Provider.SurePay, transaction));
            tempTransactionResultList.add(new TransactionResult(TransactionStatus.Failed, quickPay.calculateTransactionFee(transaction), Provider.QuickPay, transaction));
            tempTransactionResultList.add(new TransactionResult(TransactionStatus.Failed, payMasters.calculateTransactionFee(transaction), Provider.PayMasters, transaction));
            tempTransactionResultList.add(new TransactionResult(TransactionStatus.Failed, houtPay.calculateTransactionFee(transaction), Provider.HoutPay, transaction));

            //remove all transactions not processed
            tempTransactionResultList = tempTransactionResultList.stream().filter(t -> t.getTransactionFee() > 0.00).collect(Collectors.toList());

            leastProviderFee = tempTransactionResultList.stream().mapToDouble(t -> t.getTransactionFee()).min().stream().findFirst().getAsDouble();

            int index = 0;

            for (TransactionResult transactionResult : tempTransactionResultList){

                if ((transactionResult.getTransactionFee() == leastProviderFee)  ) {

                    tempTransactionResultList.set(index, new TransactionResult(TransactionStatus.Success, transactionResult.getTransactionFee(), transactionResult.getProvider(), transaction));

                    log.info("The successful transaction is  : Transaction Amount {} , Transaction Country {} , Provider Name {}, Proviser Fee {} ", transaction.getTransactionAmount(), transaction.getCountry().getName(), transactionResult.getProvider().name(), transactionResult.getTransactionFee());
                    break;
                 }
                index++;
            }

            transactionResultList.addAll(tempTransactionResultList);

            tempTransactionResultList.clear();

        }
        return transactionResultList;

    }
}
