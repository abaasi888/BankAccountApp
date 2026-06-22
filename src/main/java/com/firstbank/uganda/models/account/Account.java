package com.firstbank.uganda.models.account;

import com.firstbank.uganda.models.customer.Customer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Account {
    protected String accountNumber;
    protected Customer customer;
    protected double balance;
    protected LocalDate openingDate;
    protected double interestRate;
    protected double minimumDeposit;
    protected String accountStatus;
    protected int transactionCount;
    
    public Account(Customer customer, double openingDeposit) {
        this.customer = customer;
        this.balance = openingDeposit;
        this.openingDate = LocalDate.now();
        this.accountStatus = "ACTIVE";
        this.transactionCount = 0;
        this.minimumDeposit = minimumDeposit();
        this.accountNumber = generateAccountNumber();
    }
    
    public abstract double minimumDeposit();
    public abstract String getAccountType();
    public abstract String getFeatures();
    public abstract boolean hasOverdraft();
    public abstract double getAnnualInterestRate();
    
    public String generateAccountNumber() {
        String branchCode = customer.getBranchCode();
        int year = LocalDate.now().getYear();
        int sequence = (int)(Math.random() * 1000000);
        return String.format("%s-%d-%06d", branchCode, year, sequence);
    }
    
    public boolean validateDeposit(double deposit) {
        return deposit >= minimumDeposit;
    }
    
    public String getAccountSummary() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return String.format(
            "Account Number: %s\nAccount Type: %s\nCustomer: %s\nBranch: %s\n" +
            "Opening Date: %s\nBalance: UGX %,.0f\nMinimum Deposit: UGX %,.0f\n" +
            "Features: %s\nStatus: %s",
            accountNumber, getAccountType(), customer.getFullName(),
            customer.getBranch(), openingDate.format(formatter),
            balance, minimumDeposit, getFeatures(), accountStatus
        );
    }
    
    public String getAccountNumber() { return accountNumber; }
    public Customer getCustomer() { return customer; }
    public double getBalance() { return balance; }
    public LocalDate getOpeningDate() { return openingDate; }
    public double getInterestRate() { return interestRate; }
    public double getMinimumDeposit() { return minimumDeposit; }
    public String getAccountStatus() { return accountStatus; }
    public void setBalance(double balance) { this.balance = balance; }
}
