package com.firstbank.uganda.models.customer;

import java.time.LocalDate;
import java.time.Period;

public class Customer {
    private String firstName;
    private String lastName;
    private String nin;
    private String email;
    private String confirmEmail;
    private String phoneNumber;
    private String pin;
    private String confirmPin;
    private LocalDate dateOfBirth;
    private String accountType;
    private String branch;
    private double openingDeposit;
    private String secondNin;
    private Address address;

    public Customer() {}

    public Customer(String firstName, String lastName, String nin, String email,
                   String phoneNumber, String pin, LocalDate dateOfBirth,
                   String accountType, String branch, double openingDeposit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nin = nin;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.pin = pin;
        this.dateOfBirth = dateOfBirth;
        this.accountType = accountType;
        this.branch = branch;
        this.openingDeposit = openingDeposit;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        if (dateOfBirth == null) return 0;
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public String getBranchCode() {
        switch (branch.toLowerCase()) {
            case "kampala": return "KLA";
            case "gulu": return "GUL";
            case "mbarara": return "MBA";
            case "jinja": return "JIN";
            case "mbale": return "MBL";
            default: return "XXX";
        }
    }

    public String getCustomerSummary() {
        return String.format(
            "Customer: %s\nNIN: %s\nEmail: %s\nPhone: %s\nBranch: %s\nAccount Type: %s\nOpening Deposit: UGX %,.0f",
            getFullName(), nin, email, phoneNumber, branch, accountType, openingDeposit
        );
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getNin() { return nin; }
    public void setNin(String nin) { this.nin = nin; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getConfirmEmail() { return confirmEmail; }
    public void setConfirmEmail(String confirmEmail) { this.confirmEmail = confirmEmail; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
    public String getConfirmPin() { return confirmPin; }
    public void setConfirmPin(String confirmPin) { this.confirmPin = confirmPin; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }
    public double getOpeningDeposit() { return openingDeposit; }
    public void setOpeningDeposit(double openingDeposit) { this.openingDeposit = openingDeposit; }
    public String getSecondNin() { return secondNin; }
    public void setSecondNin(String secondNin) { this.secondNin = secondNin; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}
