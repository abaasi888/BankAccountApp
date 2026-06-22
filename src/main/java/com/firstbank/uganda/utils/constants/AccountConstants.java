package com.firstbank.uganda.utils.constants;
import java.util.HashMap;
import java.util.Map;
public class AccountConstants {
    public static final String[] ACCOUNT_TYPES = {"Savings", "Current", "Fixed Deposit", "Student", "Joint"};
    public static final String[] BRANCH_NAMES = {"Kampala", "Gulu", "Mbarara", "Jinja", "Mbale"};
    public static final String[] BRANCH_CODES = {"KLA", "GUL", "MBA", "JIN", "MBL"};
    public static final int MAX_NAME_LENGTH = 30, MIN_NAME_LENGTH = 2, NIN_LENGTH = 14;
    public static final int PIN_MIN_LENGTH = 4, PIN_MAX_LENGTH = 6, MIN_AGE = 18, MAX_AGE = 75;
    private static final Map<String, Double> MINIMUM_DEPOSITS = new HashMap<>();
    private static final Map<String, Double> INTEREST_RATES = new HashMap<>();
    static {
        MINIMUM_DEPOSITS.put("Savings", 50000.0);
        MINIMUM_DEPOSITS.put("Current", 200000.0);
        MINIMUM_DEPOSITS.put("Fixed Deposit", 1000000.0);
        MINIMUM_DEPOSITS.put("Student", 10000.0);
        MINIMUM_DEPOSITS.put("Joint", 100000.0);
        INTEREST_RATES.put("Savings", 0.045);
        INTEREST_RATES.put("Current", 0.0);
        INTEREST_RATES.put("Fixed Deposit", 0.085);
        INTEREST_RATES.put("Student", 0.025);
        INTEREST_RATES.put("Joint", 0.035);
    }
    public static double getMinimumDeposit(String accountType) { return MINIMUM_DEPOSITS.getOrDefault(accountType, 0.0); }
    public static double getInterestRate(String accountType) { return INTEREST_RATES.getOrDefault(accountType, 0.0); }
    public static boolean isValidAccountType(String accountType) {
        for (String t : ACCOUNT_TYPES) { if (t.equalsIgnoreCase(accountType)) return true; }
        return false;
    }
    public static String getBranchCode(String branch) {
        for (int i = 0; i < BRANCH_NAMES.length; i++) {
            if (BRANCH_NAMES[i].equalsIgnoreCase(branch)) return BRANCH_CODES[i];
        }
        return "XXX";
    }
}
