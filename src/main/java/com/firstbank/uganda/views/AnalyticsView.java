package com.firstbank.uganda.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import com.firstbank.uganda.controllers.database.DatabaseController;
import com.firstbank.uganda.models.account.Account;
import com.firstbank.uganda.utils.constants.UIConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsView {
    
    private Stage stage;
    private DatabaseController dbController;
    
    public AnalyticsView(DatabaseController dbController) {
        this.dbController = dbController;
        this.stage = new Stage();
    }
    
    public void show() {
        stage.setTitle("First Bank Uganda - Branch Analytics");
        stage.setWidth(750);
        stage.setHeight(600);
        
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #F5F5F5;");
        
        // Header
        Label headerLabel = new Label("Branch Analytics View");
        headerLabel.setStyle(UIConstants.getHeaderStyle());
        Label subHeaderLabel = new Label("Real-Time Account Data Dashboard");
        subHeaderLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666666;");
        
        VBox headerBox = new VBox(5, headerLabel, subHeaderLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(0, 0, 20, 0));
        mainLayout.setTop(headerBox);
        
        // Content
        VBox contentBox = new VBox(15);
        contentBox.setPadding(new Insets(10));
        contentBox.setStyle("-fx-background-color: white; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 20;");
        
        // Branch selector
        HBox branchSelector = new HBox(10);
        branchSelector.setAlignment(Pos.CENTER_LEFT);
        
        Label branchLabel = new Label("Select Branch:");
        branchLabel.setStyle("-fx-font-weight: bold;");
        
        ComboBox<String> branchCombo = new ComboBox<>();
        branchCombo.getItems().addAll("All Branches", "Kampala", "Gulu", "Mbarara", "Jinja", "Mbale");
        branchCombo.setPromptText("Select branch");
        branchCombo.setPrefWidth(200);
        branchCombo.setValue("All Branches");
        
        branchSelector.getChildren().addAll(branchLabel, branchCombo);
        
        // Analytics Display Area - Clean text only
        TextArea analyticsArea = new TextArea();
        analyticsArea.setEditable(false);
        analyticsArea.setPrefHeight(380);
        analyticsArea.setStyle(
            "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
            "-fx-font-size: 13px; " +
            "-fx-background-color: white; " +
            "-fx-border-color: #DDDDDD; " +
            "-fx-border-radius: 4px; " +
            "-fx-padding: 15;"
        );
        
        // Load initial data
        updateAnalytics("All Branches", analyticsArea);
        
        // Branch change listener
        branchCombo.setOnAction(e -> {
            String selected = branchCombo.getValue();
            if (selected != null) {
                updateAnalytics(selected, analyticsArea);
            }
        });
        
        // Refresh button
        Button refreshBtn = new Button("🔄 Refresh");
        refreshBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 20; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        refreshBtn.setOnAction(e -> {
            String selected = branchCombo.getValue();
            if (selected != null) {
                updateAnalytics(selected, analyticsArea);
            }
        });
        
        // Close button
        Button closeBtn = new Button("✕ Close");
        closeBtn.setStyle("-fx-background-color: #666666; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 20; -fx-border-radius: 4px; -fx-background-radius: 4px;");
        closeBtn.setOnAction(e -> stage.close());
        
        HBox buttonBox = new HBox(15, refreshBtn, closeBtn);
        buttonBox.setAlignment(Pos.CENTER);
        
        contentBox.getChildren().addAll(branchSelector, analyticsArea, buttonBox);
        mainLayout.setCenter(contentBox);
        
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }
    
    private void updateAnalytics(String branch, TextArea area) {
        List<Account> accounts;
        
        if (branch.equals("All Branches")) {
            accounts = dbController.getAllAccounts();
        } else {
            accounts = dbController.getAccountsByBranch(branch);
        }
        
        String report = generateCleanReport(accounts, branch);
        area.setText(report);
    }
    
    private String generateCleanReport(List<Account> accounts, String branch) {
        if (accounts.isEmpty()) {
            return "No accounts found for: " + branch + "\n\nPlease register an account first.";
        }
        
        int totalAccounts = accounts.size();
        double totalDeposits = 0;
        Map<String, Integer> accountTypeCount = new HashMap<>();
        Map<String, Double> branchDeposits = new HashMap<>();
        String mostPopularType = "";
        int maxCount = 0;
        
        for (Account acc : accounts) {
            totalDeposits += acc.getBalance();
            String type = acc.getAccountType();
            accountTypeCount.put(type, accountTypeCount.getOrDefault(type, 0) + 1);
            
            String branchName = acc.getCustomer().getBranch();
            branchDeposits.put(branchName, branchDeposits.getOrDefault(branchName, 0.0) + acc.getBalance());
        }
        
        for (Map.Entry<String, Integer> entry : accountTypeCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostPopularType = entry.getKey();
            }
        }
        
        double avgDeposit = totalDeposits / totalAccounts;
        double minDeposit = getMinDeposit(accounts);
        double maxDeposit = getMaxDeposit(accounts);
        
        // Build clean report - no box lines
        StringBuilder sb = new StringBuilder();
        
        sb.append("═══════════════════════════════════════════════════════════════\n");
        sb.append("                  FIRST BANK UGANDA - ANALYTICS              \n");
        sb.append("═══════════════════════════════════════════════════════════════\n\n");
        
        sb.append("BRANCH: " + (branch.equals("All Branches") ? "ALL BRANCHES" : branch) + "\n");
        sb.append("TOTAL ACCOUNTS: " + totalAccounts + "\n\n");
        
        sb.append("───────────────── STATISTICS ─────────────────\n\n");
        sb.append(String.format("Total Deposits   : UGX %,20.0f\n", totalDeposits));
        sb.append(String.format("Average Deposit  : UGX %,20.0f\n", avgDeposit));
        sb.append(String.format("Minimum Deposit  : UGX %,20.0f\n", minDeposit));
        sb.append(String.format("Maximum Deposit  : UGX %,20.0f\n", maxDeposit));
        sb.append(String.format("Most Popular     : %s\n\n", mostPopularType));
        
        sb.append("───────────────── ACCOUNT TYPES ─────────────────\n\n");
        for (Map.Entry<String, Integer> entry : accountTypeCount.entrySet()) {
            String type = entry.getKey();
            int count = entry.getValue();
            int percentage = (count * 100) / totalAccounts;
            sb.append(String.format("  %-12s: %3d accounts (%3d%%)\n", type, count, percentage));
        }
        
        sb.append("\n───────────────── BRANCH DEPOSITS ─────────────────\n\n");
        for (Map.Entry<String, Double> entry : branchDeposits.entrySet()) {
            String br = entry.getKey();
            double amount = entry.getValue();
            int percentage = (int)((amount * 100) / totalDeposits);
            sb.append(String.format("  %-12s: UGX %,12.0f (%3d%%)\n", br, amount, percentage));
        }
        
        sb.append("\n───────────────── RECENT ACCOUNTS ─────────────────\n\n");
        
        int displayCount = Math.min(accounts.size(), 10);
        sb.append(String.format("  %-22s %-12s %15s\n", "NAME", "TYPE", "BALANCE"));
        sb.append("  " + "-".repeat(50) + "\n");
        
        for (int i = 0; i < displayCount; i++) {
            Account acc = accounts.get(i);
            String name = acc.getCustomer().getFullName();
            String type = acc.getAccountType();
            double balance = acc.getBalance();
            sb.append(String.format("  %-22s %-12s UGX %,10.0f\n", 
                name.length() > 22 ? name.substring(0, 22) : name, 
                type, 
                balance));
        }
        
        sb.append("\n═══════════════════════════════════════════════════════════════\n");
        sb.append("Total Accounts: " + totalAccounts + "  |  Total Deposits: UGX " + String.format("%,.0f", totalDeposits) + "\n");
        sb.append("═══════════════════════════════════════════════════════════════\n");
        
        return sb.toString();
    }
    
    private double getMinDeposit(List<Account> accounts) {
        double min = Double.MAX_VALUE;
        for (Account acc : accounts) {
            if (acc.getBalance() < min) {
                min = acc.getBalance();
            }
        }
        return min == Double.MAX_VALUE ? 0 : min;
    }
    
    private double getMaxDeposit(List<Account> accounts) {
        double max = 0;
        for (Account acc : accounts) {
            if (acc.getBalance() > max) {
                max = acc.getBalance();
            }
        }
        return max;
    }
}
