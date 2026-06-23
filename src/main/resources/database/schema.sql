-- First Bank Uganda Database Schema

CREATE TABLE Accounts (
    id AUTOINCREMENT PRIMARY KEY,
    accountNumber VARCHAR(50) UNIQUE NOT NULL,
    accountType VARCHAR(20) NOT NULL,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    nin VARCHAR(14) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    pin VARCHAR(6) NOT NULL,
    dateOfBirth DATE NOT NULL,
    branch VARCHAR(30) NOT NULL,
    openingDeposit DOUBLE NOT NULL,
    currentBalance DOUBLE DEFAULT 0,
    accountStatus VARCHAR(20) DEFAULT 'ACTIVE',
    registrationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);