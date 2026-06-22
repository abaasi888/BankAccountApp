package com.firstbank.uganda.utils.constants;
public class UIConstants {
    public static final String APP_TITLE = "First Bank Uganda - Account Opening Application";
    public static final String APP_VERSION = "2.0";
    public static final String PRIMARY_COLOR = "#003366";
    public static final String SECONDARY_COLOR = "#4CAF50";
    public static final String ERROR_COLOR = "#D32F2F";
    public static final String SUCCESS_COLOR = "#2E7D32";
    public static String getHeaderStyle() { return "-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: " + PRIMARY_COLOR + ";"; }
    public static String getErrorStyle() { return "-fx-text-fill: " + ERROR_COLOR + "; -fx-font-weight: bold;"; }
    public static String getSuccessStyle() { return "-fx-text-fill: " + SUCCESS_COLOR + "; -fx-font-weight: bold;"; }
    public static String getCardStyle() { return "-fx-background-color: white; -fx-border-radius: 8px; -fx-padding: 20;"; }
}
