package com.orange.service;

import com.orange.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ProviderResultsServiceImpl implements ProviderResultService {

    @Autowired
    private TransactionResultServiceImpl transactionResultServiceImpl;


    @Override
    public List<ProviderResult> getProviderResults() {

        List<ProviderResult> providerResultList = new ArrayList<ProviderResult>();

        double sumSurePayFee  = transactionResultServiceImpl.getTransactionResults().stream().filter(i -> i.getProvider().equals(Provider.SurePay)).filter(i -> i.getStatus().equals(TransactionStatus.Success)).mapToDouble(TransactionResult::getTransactionFee).sum();
        double sumQuickPayFee  = transactionResultServiceImpl.getTransactionResults().stream().filter(i -> i.getProvider().equals(Provider.QuickPay)).filter(i -> i.getStatus().equals(TransactionStatus.Success)).mapToDouble(TransactionResult::getTransactionFee).sum();
        double sumPayMastersFee  = transactionResultServiceImpl.getTransactionResults().stream().filter(i -> i.getProvider().equals(Provider.PayMasters)).filter(i -> i.getStatus().equals(TransactionStatus.Success)).mapToDouble(TransactionResult::getTransactionFee).sum();
        double sumHoutPayFee  = transactionResultServiceImpl.getTransactionResults().stream().filter(i -> i.getProvider().equals(Provider.HoutPay)).filter(i -> i.getStatus().equals(TransactionStatus.Success)).mapToDouble(TransactionResult::getTransactionFee).sum();

        long sumSurePay  = transactionResultServiceImpl.getTransactionResults().stream().filter(i -> i.getProvider().equals(Provider.SurePay)).filter(i -> i.getStatus().equals(TransactionStatus.Success)).count();
        long sumQuickPay  = transactionResultServiceImpl.getTransactionResults().stream().filter(i -> i.getProvider().equals(Provider.QuickPay)).filter(i -> i.getStatus().equals(TransactionStatus.Success)).count();
        long sumPayMasters  = transactionResultServiceImpl.getTransactionResults().stream().filter(i -> i.getProvider().equals(Provider.PayMasters)).filter(i -> i.getStatus().equals(TransactionStatus.Success)).count();
        long sumHoutPay  = transactionResultServiceImpl.getTransactionResults().stream().filter(i -> i.getProvider().equals(Provider.HoutPay)).filter(i -> i.getStatus().equals(TransactionStatus.Success)).count();

        providerResultList.add(new ProviderResult(Provider.SurePay,sumSurePayFee,sumSurePay));
        providerResultList.add(new ProviderResult(Provider.QuickPay,sumQuickPayFee,sumQuickPay));
        providerResultList.add(new ProviderResult(Provider.PayMasters,sumPayMastersFee,sumPayMasters));
        providerResultList.add(new ProviderResult(Provider.HoutPay,sumHoutPayFee,sumHoutPay));

        return providerResultList;
    }
}
