package com.firstbank.uganda.utils.helpers;
import java.util.regex.Pattern;
public class StringUtils {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern UG_PHONE_PATTERN = Pattern.compile("^\\+256[0-9]{12}$");
    public static boolean isNullOrEmpty(String str) { return str == null || str.trim().isEmpty(); }
    public static String trim(String str) { return str == null ? "" : str.trim(); }
    public static String toUpperCase(String str) { return str == null ? "" : str.toUpperCase(); }
    public static boolean isValidEmail(String email) { return !isNullOrEmpty(email) && EMAIL_PATTERN.matcher(email.trim()).matches(); }
    public static boolean isValidPhone(String phone) { return !isNullOrEmpty(phone) && UG_PHONE_PATTERN.matcher(phone.trim()).matches(); }
    public static String getInitials(String fullName) {
        if (isNullOrEmpty(fullName)) return "";
        StringBuilder initials = new StringBuilder();
        for (String part : fullName.trim().split("\\s+")) { if (!part.isEmpty()) initials.append(part.charAt(0)); }
        return initials.toString().toUpperCase();
    }
    public static String maskString(String str, int visible) {
        if (isNullOrEmpty(str) || visible >= str.length()) return str;
        return str.substring(0, visible) + "*".repeat(str.length() - visible);
    }
}
