package com.firstbank.uganda.models.transaction;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class TransactionHistory {
    private List<Transaction> transactions;
    private String accountNumber;
    public TransactionHistory(String accountNumber) {
        this.accountNumber = accountNumber;
        this.transactions = new ArrayList<>();
    }
    public void addTransaction(Transaction transaction) { if (transaction != null) transactions.add(transaction); }
    public List<Transaction> getTransactions() { return new ArrayList<>(transactions); }
    public double getTotalDeposits() {
        return transactions.stream().filter(t -> t.getTransactionType() == TransactionType.DEPOSIT || t.getTransactionType() == TransactionType.OPENING).mapToDouble(Transaction::getAmount).sum();
    }
    public double getTotalWithdrawals() {
        return transactions.stream().filter(t -> t.getTransactionType() == TransactionType.WITHDRAWAL).mapToDouble(Transaction::getAmount).sum();
    }
    public String generateStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════\n");
        sb.append("  FIRST BANK UGANDA - STATEMENT\n");
        sb.append("  Account: ").append(accountNumber).append("\n");
        sb.append("═══════════════════════════════════════════\n");
        sb.append("Date       | Type       | Amount    | Balance\n");
        sb.append("───────────┼────────────┼───────────┼───────────\n");
        for (Transaction t : transactions) {
            sb.append(String.format("%s | %-10s | %9.0f | %9.0f\n",
                t.getTransactionDate().toLocalDate().toString(), t.getTransactionType(), t.getAmount(), t.getBalanceAfter()));
        }
        sb.append("═══════════════════════════════════════════\n");
        sb.append("Total Deposits: UGX ").append(String.format("%,.0f", getTotalDeposits())).append("\n");
        sb.append("Total Withdrawals: UGX ").append(String.format("%,.0f", getTotalWithdrawals())).append("\n");
        sb.append("═══════════════════════════════════════════\n");
        return sb.toString();
    }
}
