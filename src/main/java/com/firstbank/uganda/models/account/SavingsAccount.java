package com.firstbank.uganda.models.account;
import com.firstbank.uganda.models.customer.Customer;
public class SavingsAccount extends Account {
    private static final double MIN_DEPOSIT = 50000.0;
    private static final double INTEREST_RATE = 0.045;
    private boolean autoSweepEnabled;
    public SavingsAccount(Customer customer, double openingDeposit) {
        super(customer, openingDeposit);
        this.autoSweepEnabled = true;
        this.interestRate = INTEREST_RATE;
    }
    @Override public double minimumDeposit() { return MIN_DEPOSIT; }
    @Override public String getAccountType() { return "Savings"; }
    @Override public String getFeatures() { return "Interest " + (INTEREST_RATE * 100) + "% | No overdraft"; }
    @Override public boolean hasOverdraft() { return false; }
    @Override public double getAnnualInterestRate() { return INTEREST_RATE; }
    public boolean isAutoSweepEnabled() { return autoSweepEnabled; }
    public void toggleAutoSweep() { this.autoSweepEnabled = !this.autoSweepEnabled; }
}
