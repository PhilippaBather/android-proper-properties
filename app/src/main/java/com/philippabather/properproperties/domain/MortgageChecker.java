package com.philippabather.properproperties.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class MortgageQuoteCalculator {

    private static final NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
    final int MONTHS_IN_YEAR = 12;
    private double price;
    private double deposit;
    private double rate;
    private int duration;
    private String postcode;

    public MortgageQuoteCalculator(double price, double deposit, double rate, int duration, String postcode) {
        this.price = price;
        this.deposit = deposit;
        this.rate = rate;
        this.duration = duration;
        this.postcode = postcode;
    }

    public String calculate() {
        BigDecimal loan = BigDecimal.valueOf(price).subtract(BigDecimal.valueOf(deposit));
        BigDecimal monthlyRate = BigDecimal.valueOf(rate).divide(BigDecimal.valueOf(MONTHS_IN_YEAR), 5, RoundingMode.HALF_UP);
        BigDecimal monthlyRatePlusOne = monthlyRate.add(BigDecimal.ONE);
        int monthlyPayments = duration * MONTHS_IN_YEAR;

        BigDecimal dividend = monthlyRatePlusOne.pow(monthlyPayments).subtract(BigDecimal.ONE);
        dividend = monthlyRate.multiply(dividend);
        BigDecimal divisor = monthlyRatePlusOne.pow(monthlyPayments).subtract(BigDecimal.ONE);
        BigDecimal quotient = dividend.divide(divisor, 5, RoundingMode.HALF_UP);

        BigDecimal repayment = loan.multiply(quotient);

        return moneyFormatter.format(repayment).toString() + " p/m";
    }

}