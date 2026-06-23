First Bank Uganda - Account Opening Application
📌 Project Overview
A complete JavaFX desktop application for opening bank accounts at First Bank Uganda. This application was developed as part of the Object Oriented Programming Coursework.

👤 Development Team
Name	Role
Walusimbi Abaasi	Lead Developer
Owomugisha Nobert	Developer
Bakasinga Gerald	Developer
Mutekanga Marvin	Developer
Bbanga Samuel Emmanuel	Developer
Nabwire Jesca Recheal	Developer
Muwonge Fazil	Developer
Musumba Andrew Rodney	Developer
Waiswa Martin	Developer
Roland	Developer
📅 Date
22nd June 2026

🛠️ Technologies Used
Technology	Purpose
Java 17	Programming Language
JavaFX	GUI Framework
MS Access	Database (via UCanAccess)
Maven	Build Tool
Git/GitHub	Version Control

✨ Features Implemented
Feature	Status
Full JavaFX GUI with all required form fields	✅ Complete
Object-Oriented Design with abstract Account class	✅ Complete
5 Account subclasses (Savings, Current, Fixed Deposit, Student, Joint)	✅ Complete
Polymorphic validation using minimumDeposit() method	✅ Complete
Input validation with real-time error messages	✅ Complete
Ugandan phone format validation (+256XXXXXXXXX)	✅ Complete
Smart date picker with leap year support	✅ Complete
Account number generation (BRANCHCODE-YYYY-xxxxx)	✅ Complete
MS Access database persistence	✅ Complete
Account summary display	✅ Complete
Submit and Reset functionality	✅ Complete
Analytics View with real-time data	✅ Complete
Branch selection and routing	✅ Complete
📋 Minimum Deposit Requirements
Account Type	Minimum Deposit (UGX)	Special Rules
Savings	50,000	Earns interest, no overdraft
Current	200,000	Overdraft allowed, no interest
Fixed Deposit	1,000,000	Locked term, highest interest
Student	10,000	Age must be 18-25
Joint	100,000	Requires a second NIN
📋 Validation Rules
Field	Validation Rule
First/Last Name	Letters only, 2-30 characters
NIN	Exactly 14 alphanumeric characters, UPPERCASE
Email	Valid email format, must match confirmation
Phone	+256 followed by 9 digits
PIN	4-6 digits, not all identical
DOB	Age 18-75 (18-25 for Student accounts)
Opening Deposit	Meets minimum for selected account type
Account Type	Must be selected
Branch	Must be selected
📱 Contact
Lead Developer: Walusimbi Abaasi
Email: abaasiwaaissimom@gmail.com

🚀 How to Run
Prerequisites
Java JDK 17 or higher

Maven

Microsoft Access (for database)

Steps
bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git
cd BankAccountApp

# Ensure MS Access database is in the correct location
# src/main/resources/database/firstbank.accdb

# Place required JARs in lib/ folder:
# - ucanaccess-5.0.1.jar
# - jackcess-4.0.4.jar
# - commons-lang3-3.12.0.jar
# - commons-logging-1.2.jar
# - hsqldb-2.7.1.jar

# Compile and run
mvn clean compile javafx:run

# Alternative run command
mvn javafx:run
Running the JAR
bash
# Package the application
mvn clean package

# Run the JAR
java -jar target/bank-account-app-2.0.jar
📚 Course Information
Field	Details
Course	Object Oriented Programming
Module Code	1201ST, 1204 FST, 1301ST
Level	1.2.1.3
Date	24th June 2026
Duration	Three Weeks
Total Marks	40
Examiner	Dr. David Kakeeto
Moderator	Mr. Bazigu Alex
🔧 Troubleshooting
Database Connection Issues
Ensure firstbank.accdb is in:

text
src/main/resources/database/firstbank.accdb
UCanAccess Errors
Place all required JARs in the lib/ folder:

text
lib/
├── ucanaccess-5.0.1.jar
├── jackcess-4.0.4.jar
├── commons-lang3-3.12.0.jar
├── commons-logging-1.2.jar
└── hsqldb-2.7.1.jar
Compilation Errors
bash
# Clean and recompile
mvn clean compile

# Force dependency download
mvn clean compile -U