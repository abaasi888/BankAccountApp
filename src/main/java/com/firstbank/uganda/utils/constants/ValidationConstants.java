package com.firstbank.uganda.utils.constants;

public class ValidationConstants {
    public static final String NAME_PATTERN = "^[A-Za-z]{2,30}$";
    public static final String NIN_PATTERN = "^[A-Z0-9]{14}$";
    public static final String UG_PHONE_PATTERN = "^\\+256[0-9]{9}$";
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final String PIN_PATTERN = "^[0-9]{4,6}$";
    
    public static final String[] UNSAFE_PINS = {"1234", "12345", "123456", "0000", "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999"};
    public static final String[] UGANDAN_NAMES = {"Okello", "Ochieng", "Mukasa", "Ssebwami", "Nakato", "Nambi", "Kato", "Musoke"};
    
    public static String getErrorMessage(String code) {
        switch (code) {
            case "REQUIRED": return "This field is required.";
            case "INVALID_FORMAT": return "Invalid format.";
            case "EMAIL_MISMATCH": return "Email and Confirm Email must match.";
            case "PHONE_INVALID": return "Phone must be +256 followed by 9 digits.";
            default: return "Validation error occurred.";
        }
    }
    
    public static boolean isUnsafePIN(String pin) {
        for (String unsafe : UNSAFE_PINS) {
            if (pin.equals(unsafe)) return true;
        }
        return false;
    }
}
