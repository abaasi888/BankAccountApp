package com.firstbank.uganda.utils.helpers;
import java.text.NumberFormat;
import java.util.Locale;
public class MathUtils {
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(new Locale("en", "UG"));
    static { CURRENCY_FORMAT.setCurrency(java.util.Currency.getInstance("UGX")); }
    public static String formatCurrency(double amount) { return CURRENCY_FORMAT.format(amount); }
    public static String formatCurrencyNoSymbol(double amount) { return String.format("UGX %,.0f", amount); }
    public static double calculatePercentage(double part, double whole) { return (whole == 0) ? 0 : (part / whole) * 100; }
    public static double calculateCompoundInterest(double principal, double rate, int periods) {
        return (principal <= 0 || periods <= 0) ? 0 : principal * Math.pow(1 + rate, periods);
    }
    public static double calculateSimpleInterest(double principal, double rate, int periods) {
        return (principal <= 0 || periods <= 0) ? 0 : principal * rate * periods;
    }
}
