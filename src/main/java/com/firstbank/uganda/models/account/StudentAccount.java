package com.firstbank.uganda.models.account;
import com.firstbank.uganda.models.customer.Customer;
public class StudentAccount extends Account {
    private static final double MIN_DEPOSIT = 10000.0;
    private static final double INTEREST_RATE = 0.025;
    private static final int MAX_AGE = 25;
    private String institutionName;
    public StudentAccount(Customer customer, double openingDeposit) {
        super(customer, openingDeposit);
        this.interestRate = INTEREST_RATE;
    }
    @Override public double minimumDeposit() { return MIN_DEPOSIT; }
    @Override public String getAccountType() { return "Student"; }
    @Override public String getFeatures() { return "Interest " + (INTEREST_RATE * 100) + "% | Age: 18-25"; }
    @Override public boolean hasOverdraft() { return false; }
    @Override public double getAnnualInterestRate() { return INTEREST_RATE; }
    public boolean validateStudentEligibility() { return customer.getAge() >= 18 && customer.getAge() <= MAX_AGE; }
    public void setInstitution(String name) { this.institutionName = name; }
    public String getInstitutionName() { return institutionName; }
}
