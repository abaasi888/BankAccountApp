package com.firstbank.uganda.controllers.database;

import com.firstbank.uganda.models.account.*;
import com.firstbank.uganda.models.customer.Customer;

public class AccountFactory {
    
    public static Account createAccount(String accountType, Customer customer) {
        double deposit = customer.getOpeningDeposit();
        switch (accountType) {
            case "Savings":
                return new SavingsAccount(customer, deposit);
            case "Current":
                return new CurrentAccount(customer, deposit);
            case "Fixed Deposit":
                return new FixedDepositAccount(customer, deposit);
            case "Student":
                return new StudentAccount(customer, deposit);
            case "Joint":
                return new JointAccount(customer, deposit);
            default:
                return null;
        }
    }
    
    public static Account createAccount(String accountType, Customer customer, double deposit) {
        switch (accountType) {
            case "Savings":
                return new SavingsAccount(customer, deposit);
            case "Current":
                return new CurrentAccount(customer, deposit);
            case "Fixed Deposit":
                return new FixedDepositAccount(customer, deposit);
            case "Student":
                return new StudentAccount(customer, deposit);
            case "Joint":
                return new JointAccount(customer, deposit);
            default:
                return null;
        }
    }
    
    public static Account getRecommendedAccount(Customer customer, double deposit) {
        if (deposit >= 1000000) {
            return new FixedDepositAccount(customer, deposit);
        } else if (deposit >= 200000) {
            return new CurrentAccount(customer, deposit);
        } else if (deposit >= 50000) {
            return new SavingsAccount(customer, deposit);
        } else if (customer.getAge() >= 18 && customer.getAge() <= 25 && deposit >= 10000) {
            return new StudentAccount(customer, deposit);
        } else {
            return new SavingsAccount(customer, deposit);
        }
    }
}
