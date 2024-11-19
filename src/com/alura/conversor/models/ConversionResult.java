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

    // Getters y Setters
    public double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAmountConverted() {
        return amountConverted;
    }

    public void setAmountConverted(double amountConverted) {
        this.amountConverted = amountConverted;
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
