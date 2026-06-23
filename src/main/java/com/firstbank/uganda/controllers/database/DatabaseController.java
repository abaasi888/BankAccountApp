package com.firstbank.uganda.controllers.database;

import com.firstbank.uganda.models.account.Account;
import com.firstbank.uganda.models.customer.Customer;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    
    private static final String DB_PATH = "src/main/resources/database/firstbank.accdb";
    
    private Connection connection;
    private boolean isConnected;
    
    public DatabaseController() {
        this.isConnected = false;
        System.out.println("Initializing DatabaseController...");
        connect();
    }
    
    private void connect() {
        try {
            File dbFile = new File(DB_PATH);
            if (!dbFile.exists()) {
                System.err.println("Database not found: " + dbFile.getAbsolutePath());
                isConnected = false;
                return;
            }
            
            System.out.println("Loading UCanAccess driver...");
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("UCanAccess driver loaded!");
            
            String url = "jdbc:ucanaccess://" + dbFile.getAbsolutePath();
            System.out.println("Connecting to: " + dbFile.getAbsolutePath());
            connection = DriverManager.getConnection(url);
            isConnected = true;
            System.out.println("✅ MS Access Database connected!");
            
        } catch (ClassNotFoundException e) {
            System.err.println("UCanAccess driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public boolean saveAccount(Account account) {
        if (!isConnected) {
            System.err.println("Database not connected.");
            return false;
        }
        
        Customer customer = account.getCustomer();
        String sql = "INSERT INTO Accounts (accountNumber, accountType, firstName, lastName, nin, email, phoneNumber, pin, dateOfBirth, branch, openingDeposit, currentBalance, accountStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, account.getAccountNumber());
            ps.setString(2, account.getAccountType());
            ps.setString(3, customer.getFirstName());
            ps.setString(4, customer.getLastName());
            ps.setString(5, customer.getNin());
            ps.setString(6, customer.getEmail());
            ps.setString(7, customer.getPhoneNumber());
            ps.setString(8, customer.getPin());
            
            if (customer.getDateOfBirth() != null) {
                ps.setDate(9, Date.valueOf(customer.getDateOfBirth()));
            } else {
                ps.setNull(9, Types.DATE);
            }
            
            ps.setString(10, customer.getBranch());
            ps.setDouble(11, account.getBalance());
            ps.setDouble(12, account.getBalance());
            ps.setString(13, "ACTIVE");
            
            int rows = ps.executeUpdate();
            System.out.println("✅ Account saved to MS Access!");
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Save failed: " + e.getMessage());
            return false;
        }
    }
    
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        if (!isConnected) return accounts;
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Accounts ORDER BY registrationDate DESC")) {
            
            while (rs.next()) {
                try {
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String nin = rs.getString("nin");
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phoneNumber");
                    String pin = rs.getString("pin");
                    
                    Date dobDate = rs.getDate("dateOfBirth");
                    LocalDate dateOfBirth = null;
                    if (dobDate != null) {
                        dateOfBirth = dobDate.toLocalDate();
                    }
                    
                    String accountType = rs.getString("accountType");
                    String branch = rs.getString("branch");
                    double openingDeposit = rs.getDouble("openingDeposit");
                    
                    Customer customer = new Customer(
                        firstName != null ? firstName : "",
                        lastName != null ? lastName : "",
                        nin != null ? nin : "",
                        email != null ? email : "",
                        phoneNumber != null ? phoneNumber : "",
                        pin != null ? pin : "",
                        dateOfBirth,
                        accountType != null ? accountType : "Savings",
                        branch != null ? branch : "Kampala",
                        openingDeposit
                    );
                    
                    Account account = AccountFactory.createAccount(accountType, customer);
                    if (account != null) {
                        account.setBalance(rs.getDouble("currentBalance"));
                        accounts.add(account);
                    }
                } catch (Exception e) {
                    System.err.println("Error processing row: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println("Fetch error: " + e.getMessage());
        }
        return accounts;
    }
    
    public List<Account> getAccountsByBranch(String branch) {
        List<Account> accounts = new ArrayList<>();
        if (!isConnected) return accounts;
        
        String sql = "SELECT * FROM Accounts WHERE branch = ? ORDER BY registrationDate DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, branch);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                try {
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String nin = rs.getString("nin");
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phoneNumber");
                    String pin = rs.getString("pin");
                    
                    Date dobDate = rs.getDate("dateOfBirth");
                    LocalDate dateOfBirth = null;
                    if (dobDate != null) {
                        dateOfBirth = dobDate.toLocalDate();
                    }
                    
                    String accountType = rs.getString("accountType");
                    String branchName = rs.getString("branch");
                    double openingDeposit = rs.getDouble("openingDeposit");
                    
                    Customer customer = new Customer(
                        firstName != null ? firstName : "",
                        lastName != null ? lastName : "",
                        nin != null ? nin : "",
                        email != null ? email : "",
                        phoneNumber != null ? phoneNumber : "",
                        pin != null ? pin : "",
                        dateOfBirth,
                        accountType != null ? accountType : "Savings",
                        branchName != null ? branchName : "Kampala",
                        openingDeposit
                    );
                    
                    Account account = AccountFactory.createAccount(accountType, customer);
                    if (account != null) {
                        account.setBalance(rs.getDouble("currentBalance"));
                        accounts.add(account);
                    }
                } catch (Exception e) {
                    System.err.println("Error processing row: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println("Fetch error: " + e.getMessage());
        }
        return accounts;
    }
    
    public int getAccountCount() {
        if (!isConnected) return 0;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Accounts")) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Count error: " + e.getMessage());
        }
        return 0;
    }
    
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database closed.");
            }
        } catch (SQLException e) {
            System.err.println("Close error: " + e.getMessage());
        }
    }
    
    public boolean isConnected() { return isConnected; }
}
