package com.firstbank.uganda.models.account;
import com.firstbank.uganda.models.customer.Customer;
import java.util.ArrayList;
import java.util.List;
public class JointAccount extends Account {
    private static final double MIN_DEPOSIT = 100000.0;
    private static final double INTEREST_RATE = 0.035;
    private String secondNin;
    private String secondCustomerName;
    private List<String> jointHolders;
    public JointAccount(Customer customer, double openingDeposit) {
        super(customer, openingDeposit);
        this.interestRate = INTEREST_RATE;
        this.jointHolders = new ArrayList<>();
        this.jointHolders.add(customer.getFullName());
    }
    @Override public double minimumDeposit() { return MIN_DEPOSIT; }
    @Override public String getAccountType() { return "Joint"; }
    @Override public String getFeatures() { return "Shared liability | " + jointHolders.size() + " holder(s)"; }
    @Override public boolean hasOverdraft() { return false; }
    @Override public double getAnnualInterestRate() { return INTEREST_RATE; }
    public void setSecondHolder(String nin, String name) {
        this.secondNin = nin;
        this.secondCustomerName = name;
        this.jointHolders.add(name);
    }
    public List<String> getJointHolders() { return new ArrayList<>(jointHolders); }
}
