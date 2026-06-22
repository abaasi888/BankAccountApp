package com.firstbank.uganda.models.account;
import com.firstbank.uganda.models.customer.Customer;
public class CurrentAccount extends Account {
    private static final double MIN_DEPOSIT = 200000.0;
    private double overdraftLimit;
    private int creditScore;
    public CurrentAccount(Customer customer, double openingDeposit) {
        super(customer, openingDeposit);
        this.creditScore = calculateCreditScore();
        this.overdraftLimit = calculateOverdraftLimit();
        this.interestRate = 0.0;
    }
    private int calculateCreditScore() {
        int score = 500;
        if (customer.getAge() >= 30 && customer.getAge() <= 50) score += 100;
        if (customer.getOpeningDeposit() >= 1000000) score += 150;
        return Math.min(score, 850);
    }
    private double calculateOverdraftLimit() {
        if (creditScore >= 750) return 1000000.0;
        if (creditScore >= 650) return 750000.0;
        if (creditScore >= 550) return 500000.0;
        return 250000.0;
    }
    @Override public double minimumDeposit() { return MIN_DEPOSIT; }
    @Override public String getAccountType() { return "Current"; }
    @Override public String getFeatures() { return "Overdraft allowed | Credit Score: " + creditScore; }
    @Override public boolean hasOverdraft() { return true; }
    @Override public double getAnnualInterestRate() { return 0.0; }
    public double getOverdraftLimit() { return overdraftLimit; }
    public int getCreditScore() { return creditScore; }
}
