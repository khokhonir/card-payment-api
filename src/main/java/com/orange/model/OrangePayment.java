package com.orange.model;

@lombok.Getter
@lombok.Setter
@lombok.ToString
public class OrangePayment {
    private String generatedRevenue;
    private String totalValueOfAllTransactions;
    private String cashPaidToProviders;
}
