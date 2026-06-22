package com.firstbank.uganda.models.account;
import com.firstbank.uganda.models.customer.Customer;
import java.time.LocalDate;
public class FixedDepositAccount extends Account {
    private static final double MIN_DEPOSIT = 1000000.0;
    private static final double INTEREST_RATE = 0.085;
    private LocalDate maturityDate;
    public FixedDepositAccount(Customer customer, double openingDeposit) {
        super(customer, openingDeposit);
        this.interestRate = INTEREST_RATE;
        this.maturityDate = openingDate.plusMonths(12);
    }
    @Override public double minimumDeposit() { return MIN_DEPOSIT; }
    @Override public String getAccountType() { return "Fixed Deposit"; }
    @Override public String getFeatures() { return "Highest interest " + (INTEREST_RATE * 100) + "% | Locked term"; }
    @Override public boolean hasOverdraft() { return false; }
    @Override public double getAnnualInterestRate() { return INTEREST_RATE; }
    public double calculateMaturityValue() { return balance * (1 + INTEREST_RATE); }
    public LocalDate getMaturityDate() { return maturityDate; }
}
