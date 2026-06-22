# First Bank Uganda - Account Opening Application

## 📌 Project Overview
A complete JavaFX desktop application for opening bank accounts at First Bank Uganda.

## 👤 Developers
**Walusimbi Abaasi**
**Owomugisha Nobert**
**Bakasinga Gerald**
**Mutekanga Marvin**
**Bbanga Samuel Emmanuel**
**Nabwire Jesca Recheal**
**Muwonge Fazil**
**Musumba Andrew Rodney**
**Waiswa Martin**
**Roland**

## 📅 Date
22nd June 2026

## 🛠️ Technologies Used
- Java 17 -Programming Language
- JavaFX  -GUI Framework
- HSQLDB  -Embedded Database
- Maven   -Build Tool

## 📁 Project Structure
BankAccountApp/
├── src/main/java/com/firstbank/uganda/
│ ├── models/
│ │ ├── account/ # Account abstract class & subclasses
│ │ ├── customer/ # Customer entity
│ │ └── transaction/ # Transaction history
│ ├── controllers/
│ │ ├── validation/ # Input validation logic
│ │ ├── database/ # Database operations
│ │ └── routing/ # SmartBranch routing
│ ├── views/ # JavaFX UI
│ └── utils/ # Constants, Helpers, Generators
├── src/main/resources/ # Properties files
├── pom.xml # Maven configuration
└── README.md # Documentation

## ✨ Features Implemented
- ✅ Full JavaFX GUI with all required form fields
- ✅ Object-Oriented Design with abstract Account class
- ✅ 5 Account subclasses (Savings, Current, Fixed Deposit, Student, Joint)
- ✅ Polymorphic validation using minimumDeposit() method
- ✅ Input validation with real-time error messages
- ✅ Ugandan phone format validation (+256XXXXXXXXX)
- ✅ Smart date picker with leap year support
- ✅ Account number generation (BRANCHCODE-YYYY-xxxxx)
- ✅ HSQLDB database persistence
- ✅ Account summary display
- ✅ Submit and Reset functionality

## 🚀 How to Run
### Prerequisites
- Java JDK 17 or higher
- Maven

### Steps
```bash
# Clone the repository
git clone <your-repo-url>
cd BankAccountApp

# Compile and run
mvn clean compile javafx:run


## 📚 Course Information
- **Course:** Object Oriented Programming
- **Module Code:** 1201ST, 1204 FST, 1301ST
- **Date:** 24th June 2026
