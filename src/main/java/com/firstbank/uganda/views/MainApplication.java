package com.firstbank.uganda.views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import com.firstbank.uganda.controllers.database.DatabaseController;
import com.firstbank.uganda.controllers.database.AccountFactory;
import com.firstbank.uganda.controllers.validation.SmartValidationController;
import com.firstbank.uganda.controllers.validation.ValidationResult;
import com.firstbank.uganda.models.account.Account;
import com.firstbank.uganda.models.customer.Customer;
import com.firstbank.uganda.utils.helpers.DateUtils;
import com.firstbank.uganda.utils.constants.UIConstants;

import java.time.LocalDate;

public class MainApplication extends Application {
    private TextField firstNameField, lastNameField, ninField, emailField, confirmEmailField, phoneField, depositField;
    private PasswordField pinField, confirmPinField;
    private ComboBox<Integer> yearCombo, monthCombo, dayCombo;
    private ComboBox<String> accountTypeCombo, branchCombo;
    private TextArea summaryArea;
    private Label statusLabel;
    private DatabaseController dbController;
    private SmartValidationController validator;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        dbController = new DatabaseController();
        validator = new SmartValidationController();

        primaryStage.setTitle(UIConstants.APP_TITLE);
        primaryStage.setWidth(950);
        primaryStage.setHeight(750);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #F5F5F5;");

        // Header
        Label headerLabel = new Label("First Bank Uganda");
        headerLabel.setStyle(UIConstants.getHeaderStyle());
        Label subHeaderLabel = new Label("New Account Opening Application Form");
        subHeaderLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #666666;");
        VBox headerBox = new VBox(5, headerLabel, subHeaderLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(0, 0, 20, 0));
        mainLayout.setTop(headerBox);

        // Form
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(15);
        grid.setVgap(12);
        grid.setStyle(UIConstants.getCardStyle());

        int row = 0;
        Label personalLabel = new Label("Personal Information");
        personalLabel.setStyle(UIConstants.getHeaderStyle());
        personalLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        GridPane.setColumnSpan(personalLabel, 4);
        grid.add(personalLabel, 0, row++);

        grid.add(new Label("First Name:"), 0, row);
        firstNameField = new TextField(); firstNameField.setPromptText("Enter first name"); firstNameField.setPrefWidth(200);
        grid.add(firstNameField, 1, row);
        grid.add(new Label("Last Name:"), 2, row);
        lastNameField = new TextField(); lastNameField.setPromptText("Enter last name");
        grid.add(lastNameField, 3, row++);

        grid.add(new Label("NIN:"), 0, row);
        ninField = new TextField(); ninField.setPromptText("14 alphanumeric characters"); ninField.setPrefWidth(200);
        grid.add(ninField, 1, row);
        grid.add(new Label("Phone:"), 2, row);
        phoneField = new TextField(); phoneField.setPromptText("+256XXXXXXXXXX");
        grid.add(phoneField, 3, row++);

        grid.add(new Label("Email:"), 0, row);
        emailField = new TextField(); emailField.setPromptText("Enter email"); emailField.setPrefWidth(200);
        grid.add(emailField, 1, row);
        grid.add(new Label("Confirm Email:"), 2, row);
        confirmEmailField = new TextField(); confirmEmailField.setPromptText("Re-enter email");
        grid.add(confirmEmailField, 3, row++);

        grid.add(new Label("PIN:"), 0, row);
        pinField = new PasswordField(); pinField.setPromptText("4-6 digits");
        grid.add(pinField, 1, row);
        grid.add(new Label("Confirm PIN:"), 2, row);
        confirmPinField = new PasswordField(); confirmPinField.setPromptText("Re-enter PIN");
        grid.add(confirmPinField, 3, row++);

        grid.add(new Separator(), 0, row++);
        GridPane.setColumnSpan(new Separator(), 4);

        Label dobLabel = new Label("Date of Birth");
        dobLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        GridPane.setColumnSpan(dobLabel, 4);
        grid.add(dobLabel, 0, row++);
        grid.add(new Label("Year:"), 0, row);
        yearCombo = new ComboBox<>(); yearCombo.setPrefWidth(120);
        grid.add(yearCombo, 1, row);
        grid.add(new Label("Month:"), 2, row);
        monthCombo = new ComboBox<>(); monthCombo.setPrefWidth(120);
        grid.add(monthCombo, 3, row++);
        grid.add(new Label("Day:"), 0, row);
        dayCombo = new ComboBox<>(); dayCombo.setPrefWidth(120);
        grid.add(dayCombo, 1, row);
        grid.add(new Separator(), 0, row++);
        GridPane.setColumnSpan(new Separator(), 4);

        Label accountLabel = new Label("Account Details");
        accountLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        GridPane.setColumnSpan(accountLabel, 4);
        grid.add(accountLabel, 0, row++);

        grid.add(new Label("Account Type:"), 0, row);
        accountTypeCombo = new ComboBox<>();
        accountTypeCombo.getItems().addAll("Savings", "Current", "Fixed Deposit", "Student", "Joint");
        accountTypeCombo.setPromptText("Select account type");
        accountTypeCombo.setPrefWidth(200);
        grid.add(accountTypeCombo, 1, row);
        grid.add(new Label("Branch:"), 2, row);
        branchCombo = new ComboBox<>();
        branchCombo.getItems().addAll("Kampala", "Gulu", "Mbarara", "Jinja", "Mbale");
        branchCombo.setPromptText("Select branch");
        branchCombo.setPrefWidth(200);
        grid.add(branchCombo, 3, row++);

        grid.add(new Label("Opening Deposit (UGX):"), 0, row);
        depositField = new TextField(); depositField.setPromptText("Enter deposit amount"); depositField.setPrefWidth(200);
        grid.add(depositField, 1, row);

        statusLabel = new Label("");
        statusLabel.setStyle(UIConstants.getErrorStyle());
        GridPane.setColumnSpan(statusLabel, 4);
        GridPane.setRowIndex(statusLabel, ++row);
        grid.add(statusLabel, 0, row);

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        mainLayout.setCenter(scrollPane);

        // Bottom
        VBox bottomBox = new VBox(10);
        bottomBox.setPadding(new Insets(20, 0, 0, 0));

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        Button submitBtn = new Button("Submit Application");
        submitBtn.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 30;");
        submitBtn.setOnAction(e -> handleSubmit());
        Button resetBtn = new Button("Reset Form");
        resetBtn.setStyle("-fx-background-color: #666666; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 30;");
        resetBtn.setOnAction(e -> resetForm());
        buttonBox.getChildren().addAll(submitBtn, resetBtn);
        bottomBox.getChildren().add(buttonBox);

        Label summaryLabel = new Label("Account Summary is Below:");
        summaryLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        summaryArea = new TextArea();
        summaryArea.setEditable(false);
        summaryArea.setPrefHeight(150);
        summaryArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12px;");
        bottomBox.getChildren().addAll(summaryLabel, summaryArea);
        mainLayout.setBottom(bottomBox);

        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Setup comboboxes
        int currentYear = DateUtils.getCurrentYear();
        for (int y = currentYear - 100; y <= currentYear; y++) yearCombo.getItems().add(y);
        yearCombo.setValue(currentYear);
        for (int m = 1; m <= 12; m++) monthCombo.getItems().add(m);
        monthCombo.setValue(1);
        updateDays(1, currentYear);
        monthCombo.setOnAction(e -> { Integer m = monthCombo.getValue(), y = yearCombo.getValue(); if (m != null && y != null) updateDays(m, y); });
        yearCombo.setOnAction(e -> { Integer m = monthCombo.getValue(), y = yearCombo.getValue(); if (m != null && y != null) updateDays(m, y); });
    }

    private void updateDays(int month, int year) {
        dayCombo.getItems().clear();
        for (int d = 1; d <= DateUtils.getDaysInMonth(month, year); d++) dayCombo.getItems().add(d);
        if (!dayCombo.getItems().isEmpty()) dayCombo.setValue(1);
    }

    private void handleSubmit() {
        try {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String nin = ninField.getText().trim().toUpperCase();
            String email = emailField.getText().trim();
            String confirmEmail = confirmEmailField.getText().trim();
            String phone = phoneField.getText().trim();
            String pin = pinField.getText().trim();
            String confirmPin = confirmPinField.getText().trim();
            Integer year = yearCombo.getValue(), month = monthCombo.getValue(), day = dayCombo.getValue();
            String accountType = accountTypeCombo.getValue();
            String branch = branchCombo.getValue();
            String depositStr = depositField.getText().trim();

            // Validation
            if (firstName.isEmpty()) { showError("First Name is required."); return; }
            if (lastName.isEmpty()) { showError("Last Name is required."); return; }
            if (nin.isEmpty()) { showError("NIN is required."); return; }
            if (email.isEmpty()) { showError("Email is required."); return; }
            if (!email.equals(confirmEmail)) { showError("Email and Confirm Email must match."); return; }
            if (phone.isEmpty()) { showError("Phone is required."); return; }
            if (pin.isEmpty()) { showError("PIN is required."); return; }
            if (!pin.equals(confirmPin)) { showError("PIN and Confirm PIN must match."); return; }
            if (year == null || month == null || day == null) { showError("Date of Birth is required."); return; }
            if (accountType == null) { showError("Account Type is required."); return; }
            if (branch == null) { showError("Branch is required."); return; }
            if (depositStr.isEmpty()) { showError("Opening Deposit is required."); return; }

            double deposit = Double.parseDouble(depositStr);
            LocalDate dob = LocalDate.of(year, month, day);
            Customer customer = new Customer(firstName, lastName, nin, email, phone, pin, dob, accountType, branch, deposit);
            Account account = AccountFactory.createAccount(accountType, customer, deposit);
            if (account == null) { showError("Invalid account type."); return; }

            ValidationResult result = validator.validateAll(customer, account);
            if (!result.isPassed()) { showError("Validation Errors:\n" + result.getSummary()); return; }

            // Save to database
            boolean saved = dbController.saveAccount(account);
            
            String summary = account.getAccountSummary() + "\n\n" + result.getSummary();
            if (saved) {
                summary += "\n\n✅ Account saved to database successfully!";
                statusLabel.setText("✓ Application submitted and saved to database!");
                statusLabel.setStyle(UIConstants.getSuccessStyle());
            } else {
                summary += "\n\n⚠️ Database not connected. Account saved in memory only.";
                statusLabel.setText("⚠️ Application submitted but database not connected!");
                statusLabel.setStyle("-fx-text-fill: #FF9800; -fx-font-weight: bold;");
            }
            
            summaryArea.setText(summary);
            pinField.clear();
            confirmPinField.clear();
            
        } catch (NumberFormatException e) {
            showError("Opening Deposit must be a valid number.");
        } catch (Exception e) {
            showError("Error: " + e.getMessage());
        }
    }

    private void showError(String message) {
        statusLabel.setText("✗ " + message);
        statusLabel.setStyle(UIConstants.getErrorStyle());
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetForm() {
        firstNameField.clear(); lastNameField.clear(); ninField.clear();
        emailField.clear(); confirmEmailField.clear(); phoneField.clear();
        pinField.clear(); confirmPinField.clear(); depositField.clear();
        summaryArea.clear();
        accountTypeCombo.setValue(null); branchCombo.setValue(null);
        yearCombo.setValue(DateUtils.getCurrentYear()); monthCombo.setValue(1);
        updateDays(1, DateUtils.getCurrentYear());
        statusLabel.setText("");
    }

    @Override
    public void stop() { if (dbController != null) dbController.close(); }
}
