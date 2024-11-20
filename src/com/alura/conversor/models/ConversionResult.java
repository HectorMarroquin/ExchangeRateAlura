package com.alura.conversor.models;

public class ConversionResult {

    private double originalAmount;
    private double rate;
    private double amountConverted;

    public ConversionResult(double originalAmount, double rate, double amountConverted) {
        this.originalAmount = originalAmount;
        this.rate = rate;
        this.amountConverted = amountConverted;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public double getRate() {
        return rate;
    }

    public double getAmountConverted() {
        return amountConverted;
    }

    @Override
    public String toString() {
        return "ConversionResult{" +
                "originalAmount=" + originalAmount +
                ", rate=" + rate +
                ", amountConverted=" + amountConverted +
                '}';
    }
}
