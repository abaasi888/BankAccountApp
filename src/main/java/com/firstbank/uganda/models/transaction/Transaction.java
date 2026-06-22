package com.firstbank.uganda.models.transaction;
import java.time.LocalDateTime;
public class Transaction {
    private String transactionId, accountNumber, description, referenceNumber, status;
    private TransactionType transactionType;
    private double amount, balanceAfter;
    private LocalDateTime transactionDate;
    public Transaction(String accountNumber, TransactionType type, double amount, double balanceAfter, String description) {
        this.transactionId = "TXN-" + System.currentTimeMillis();
        this.accountNumber = accountNumber;
        this.transactionType = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.transactionDate = LocalDateTime.now();
        this.status = "COMPLETED";
        this.referenceNumber = "REF-" + System.currentTimeMillis();
    }
    public String getTransactionSummary() {
        return String.format("Transaction: %s\nType: %s\nAmount: UGX %,.0f\nBalance: UGX %,.0f\nStatus: %s",
            transactionId, transactionType, amount, balanceAfter, status);
    }
    public boolean isApproved() { return "COMPLETED".equals(status) || "APPROVED".equals(status); }
    // Getters
    public String getTransactionId() { return transactionId; }
    public String getAccountNumber() { return accountNumber; }
    public TransactionType getTransactionType() { return transactionType; }
    public double getAmount() { return amount; }
    public double getBalanceAfter() { return balanceAfter; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public String getDescription() { return description; }
    public String getReferenceNumber() { return referenceNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
