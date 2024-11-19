package com.alura.conversor.models;

public class ConversionOption {

    private Currency fromCurrency;
    private Currency toCurrency;

    public ConversionOption(Currency fromCurrency, Currency toCurrency) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }
}
