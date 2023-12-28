package com.philippabather.properproperties.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * MortgageChecker - calcula el pago mensual de una hipoteca.
 *
 * @author Philippa Bather
 */
public class MortgageChecker {

    private static final NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
    public static final int MONTHS_IN_YEAR = 12;

    /**
     * Calcula el pago mensual de una hipoteca usando la fórmula:
     *
     * H = P * (t(1 + t)^n / (1 + t)^n - 1)
     *
     * H = hipoteca, P = préstamo, t = tasa de interés, n = número de pagos mensuales
     *
     * @param price - precio del inmueble
     * @param deposit - déposito
     * @param rate - tasa de interés (%)
     * @param duration - duración del préstamo
     * @return pago mensual formatado en euros
     */
    public static String calculate(double price, double deposit, double rate, int duration) {
        BigDecimal loan = BigDecimal.valueOf(price).subtract(BigDecimal.valueOf(deposit));  // préstamo
        BigDecimal monthlyRate = BigDecimal.valueOf(rate).divide(BigDecimal.valueOf(MONTHS_IN_YEAR), 5, RoundingMode.HALF_UP);  // tasa mensual
        BigDecimal monthlyRatePlusOne = monthlyRate.add(BigDecimal.ONE);  // tasa mensual más 1
        int monthlyPayments = duration * MONTHS_IN_YEAR; // número de pagos mensuales

        BigDecimal dividend = monthlyRatePlusOne.pow(monthlyPayments).subtract(BigDecimal.ONE);
        dividend = monthlyRate.multiply(dividend);
        BigDecimal divisor = monthlyRatePlusOne.pow(monthlyPayments).subtract(BigDecimal.ONE);
        BigDecimal quotient = dividend.divide(divisor, 5, RoundingMode.HALF_UP);  // cociente

        BigDecimal monthlyRepayment = loan.multiply(quotient);  // reembolso mensual

        return moneyFormatter.format(monthlyRepayment).toString() + " p/m";
    }
}