package com.orange.model;


@lombok.Getter
@lombok.Setter
@lombok.ToString
public class ProviderResult {

    private Provider provider;
    private double totalFee;
    private Integer numberOfTransactions;

    public ProviderResult(){}

    public ProviderResult(Provider provider, double totalFee, Integer numberOfTransactions) {
        this.provider = provider;
        this.totalFee = totalFee;
        this.numberOfTransactions = numberOfTransactions;

    }


}
