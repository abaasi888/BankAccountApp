package com.firstbank.uganda.controllers.routing;
import com.firstbank.uganda.models.customer.Customer;
import java.util.HashMap;
import java.util.Map;
public class SmartBranchRouter {
    private static final String[] BRANCH_CODES = {"KLA", "GUL", "MBA", "JIN", "MBL"};
    private static final int[] BRANCH_CAPACITIES = {5000, 2000, 3000, 4500, 2500};
    private Map<String, Integer> currentUsage;
    public SmartBranchRouter() { this.currentUsage = new HashMap<>(); for (String code : BRANCH_CODES) currentUsage.put(code, 0); }
    public String getOptimalBranch(Customer customer) {
        String phone = customer.getPhoneNumber();
        if (phone.startsWith("+25677") || phone.startsWith("+25670")) return "Kampala";
        if (phone.startsWith("+25678") || phone.startsWith("+25675")) return "Gulu";
        if (phone.startsWith("+25671") || phone.startsWith("+25672")) return "Mbarara";
        if (phone.startsWith("+25676") || phone.startsWith("+25674")) return "Jinja";
        return "Mbale";
    }
    public String getBranchRiskLevel(String branch) {
        String code = getBranchCode(branch);
        int usage = currentUsage.getOrDefault(code, 0);
        int capacity = getBranchCapacity(code);
        double pct = capacity > 0 ? (double) usage / capacity * 100 : 0;
        if (pct > 80) return "HIGH";
        if (pct > 60) return "MEDIUM";
        return "LOW";
    }
    private String getBranchCode(String branch) {
        switch (branch.toLowerCase()) {
            case "kampala": return "KLA";
            case "gulu": return "GUL";
            case "mbarara": return "MBA";
            case "jinja": return "JIN";
            case "mbale": return "MBL";
            default: return "XXX";
        }
    }
    private int getBranchCapacity(String code) {
        for (int i = 0; i < BRANCH_CODES.length; i++) {
            if (BRANCH_CODES[i].equals(code)) return BRANCH_CAPACITIES[i];
        }
        return 1000;
    }
    public String getBranchRecommendation(String branch) {
        String risk = getBranchRiskLevel(branch);
        if ("HIGH".equals(risk)) return "Branch near capacity. Consider alternative.";
        if ("MEDIUM".equals(risk)) return "Branch has moderate usage. Good choice.";
        return "Branch has low usage. Excellent choice!";
    }
}
