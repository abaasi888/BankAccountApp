package com.firstbank.uganda.utils.helpers;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
public class DateUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    public static int getDaysInMonth(int month, int year) { return (month < 1 || month > 12) ? 0 : YearMonth.of(year, month).lengthOfMonth(); }
    public static boolean isLeapYear(int year) { return YearMonth.of(year, 1).isLeapYear(); }
    public static int getCurrentYear() { return LocalDate.now().getYear(); }
    public static int getAge(LocalDate birthDate) { return (birthDate == null) ? 0 : (int) java.time.temporal.ChronoUnit.YEARS.between(birthDate, LocalDate.now()); }
    public static String formatDate(LocalDate date) { return (date == null) ? "" : date.format(DATE_FORMATTER); }
    public static boolean isWeekend(LocalDate date) { if (date == null) return false; int d = date.getDayOfWeek().getValue(); return d == 6 || d == 7; }
}
