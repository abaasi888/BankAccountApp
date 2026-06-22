package com.firstbank.uganda.controllers.validation;

import com.firstbank.uganda.models.account.Account;
import com.firstbank.uganda.models.customer.Customer;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SmartValidationController {
    
    private List<ValidationError> errors;
    private List<ValidationWarning> warnings;
    private int validationScore;
    private boolean hasErrors;
    
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z]{2,30}$");
    private static final Pattern NIN_PATTERN = Pattern.compile("^[A-Z0-9]{14}$");
    private static final Pattern UG_PHONE_PATTERN = Pattern.compile("^\\+256[0-9]{9}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PIN_PATTERN = Pattern.compile("^[0-9]{4,6}$");
    
    public SmartValidationController() {
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
        this.validationScore = 100;
        this.hasErrors = false;
    }
    
    public ValidationResult validateAll(Customer customer, Account account) {
        errors.clear();
        warnings.clear();
        validationScore = 100;
        hasErrors = false;
        
        validateName(customer.getFirstName(), "First Name");
        validateName(customer.getLastName(), "Last Name");
        validateNIN(customer.getNin());
        validateEmail(customer.getEmail());
        validatePhoneNumber(customer.getPhoneNumber());
        validatePIN(customer.getPin());
        validateDateOfBirth(customer.getDateOfBirth(), customer.getAccountType());
        validateOpeningDeposit(account);
        
        return new ValidationResult(errors, warnings, validationScore);
    }
    
    private void validateName(String name, String fieldName) {
        if (name == null || name.trim().isEmpty()) {
            addError(fieldName + " is required.");
            validationScore -= 5;
            return;
        }
        String trimmed = name.trim();
        if (!NAME_PATTERN.matcher(trimmed).matches()) {
            addError(fieldName + " must contain only letters and be 2-30 characters.");
            validationScore -= 5;
        }
    }
    
    private void validateNIN(String nin) {
        if (nin == null || nin.trim().isEmpty()) {
            addError("NIN is required.");
            validationScore -= 10;
            return;
        }
        String trimmed = nin.trim().toUpperCase();
        if (!NIN_PATTERN.matcher(trimmed).matches()) {
            addError("NIN must be exactly 14 alphanumeric characters and UPPERCASE.");
            validationScore -= 10;
        }
    }
    
    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            addError("Email is required.");
            validationScore -= 5;
            return;
        }
        String trimmed = email.trim();
        if (!EMAIL_PATTERN.matcher(trimmed).matches()) {
            addError("Invalid email format.");
            validationScore -= 5;
        }
    }
    
    private void validatePhoneNumber(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            addError("Phone Number is required.");
            validationScore -= 5;
            return;
        }
        String trimmed = phone.trim();
        if (!UG_PHONE_PATTERN.matcher(trimmed).matches()) {
            addError("Phone Number must follow format +256XXXXXXXXX (9 digits after +256).");
            validationScore -= 5;
        }
    }
    
    private void validatePIN(String pin) {
        if (pin == null || pin.trim().isEmpty()) {
            addError("PIN is required.");
            validationScore -= 5;
            return;
        }
        String trimmed = pin.trim();
        if (!PIN_PATTERN.matcher(trimmed).matches()) {
            addError("PIN must be 4-6 digits.");
            validationScore -= 5;
            return;
        }
        if (trimmed.matches("^(.)\\1*$")) {
            addError("PIN cannot be all-identical digits.");
            validationScore -= 10;
        }
    }
    
    private void validateDateOfBirth(LocalDate dob, String accountType) {
        if (dob == null) {
            addError("Date of Birth is required.");
            validationScore -= 5;
            return;
        }
        int age = Period.between(dob, LocalDate.now()).getYears();
        if (age < 18 || age > 75) {
            addError("Age must be between 18 and 75 years.");
            validationScore -= 5;
            return;
        }
        if ("Student".equals(accountType) && (age < 18 || age > 25)) {
            addError("Student account requires age between 18 and 25 years.");
            validationScore -= 10;
        }
    }
    
    private void validateOpeningDeposit(Account account) {
        if (account == null) {
            addError("Opening Deposit is required.");
            validationScore -= 5;
            return;
        }
        double deposit = account.getBalance();
        double minimum = account.minimumDeposit();
        if (deposit < minimum) {
            addError("Opening Deposit must be at least UGX " +
                    String.format("%,.0f", minimum) +
                    " for " + account.getAccountType() + " account.");
            validationScore -= 10;
        }
    }
    
    private void addError(String message) {
        errors.add(new ValidationError(message, ValidationError.Severity.ERROR));
        hasErrors = true;
    }
    
    public boolean hasErrors() { return hasErrors; }
    public int getValidationScore() { return Math.max(0, validationScore); }
    public List<ValidationError> getErrors() { return errors; }
    public List<ValidationWarning> getWarnings() { return warnings; }
}
