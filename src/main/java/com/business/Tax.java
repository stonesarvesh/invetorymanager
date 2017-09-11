package com.business;

public class Tax {
    private String taxType;
    private double taxRate;

    public Tax(String taxType, double taxRate) {
        this.taxRate = taxRate;
        this.taxType = taxType;
    }


    public double getTaxRate() {
        return taxRate;
    }

    public String getTaxType() {
        return taxType;
    }


}
