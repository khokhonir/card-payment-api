package com.orange.model;

@lombok.Getter
@lombok.Setter
@lombok.ToString
public class ProviderResult {

    private Provider provider;
    private Double totalFee;
    private Long numberOfTransactions;

    public ProviderResult(Provider provider, double totalFee, Long numberOfTransactions){
        this.provider = provider;
        this.totalFee = totalFee;
        this.numberOfTransactions = numberOfTransactions;
    }
}
