package com.firstbank.uganda.utils.generators;
import com.firstbank.uganda.models.account.Account;
import com.firstbank.uganda.models.customer.Customer;
import java.time.Year;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
public class SmartAccountNumberGenerator {
    private static final ConcurrentHashMap<String, AtomicInteger> branchCounters = new ConcurrentHashMap<>();
    private static final String[] BRANCH_CODES = {"KLA", "GUL", "MBA", "JIN", "MBL"};
    static { int year = Year.now().getValue(); for (String code : BRANCH_CODES) branchCounters.put(code + "-" + year, new AtomicInteger(0)); }
    public static synchronized String generate(Account account) {
        Customer customer = account.getCustomer();
        String branchCode = getBranchCode(customer.getBranch());
        int year = Year.now().getValue();
        String key = branchCode + "-" + year;
        AtomicInteger counter = branchCounters.computeIfAbsent(key, k -> new AtomicInteger(0));
        int sequence = counter.incrementAndGet();
        String typeCode = getAccountTypeCode(account.getAccountType());
        return String.format("%s-%s-%d-%06d", typeCode, branchCode, year, sequence);
    }
    private static String getBranchCode(String branch) {
        switch (branch.toLowerCase()) {
            case "kampala": return "KLA"; case "gulu": return "GUL";
            case "mbarara": return "MBA"; case "jinja": return "JIN";
            case "mbale": return "MBL"; default: return "XXX";
        }
    }
    private static String getAccountTypeCode(String type) {
        switch (type.toLowerCase()) {
            case "savings": return "SAV"; case "current": return "CUR";
            case "fixed deposit": return "FXD"; case "student": return "STU";
            case "joint": return "JNT"; default: return "ACC";
        }
    }
}
