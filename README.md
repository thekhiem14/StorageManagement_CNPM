# Storage Management System

## 📌 Introduction

Course project for **Introduction to Software Engineering** <br>
A **desktop** application built with **Java Swing + JDBC** to manage inventory items, invoices, users, and basic statistics.

> Note: This project is **not** a JSP/Servlet web app. It is a Swing desktop app using raw JDBC.

## 🏗️ Main Features

* User authentication & management
* Invoice management
* Statistics & simple reports

## 🛠️ Tech Stack

* **Language:** Java 8+ (or 11)
* **GUI:** Swing
* **Database:** MySQL 8.x
* **Data Access:** JDBC (raw)
* **Testing:** JUnit 4.13.2
* **IDE:** IntelliJ IDEA

## 📂 Project Structure

```
Code/
  dao/
    DAO.java
    InvoiceDAO.java
    SubAgentStatDAO.java
    UserDAO.java
  model/
    Invoice.java
    InvoiceItem.java
    Item.java
    SubAgent.java
    SubAgentStat.java
    User.java
  view/
    Item/
      InvoiceFrm.java
      SubAgentStatFrm.java
    user/
      LoginFrm.java
      ManagerHomeFrm.java
      StatisticFrm.java
  test/
    unit/
      InvoiceDAOTest.java
      SubAgentDAOTest.java
      UserDAOTest.java

Junit/                   
mysql_jdbc/              
sqljdbc_12.6.1.0_enu/ JDBC driver

.gitignore
README.md
```

## ⚙️ Setup & Run

1. **Prerequisites**

   * JDK 8 or 11 installed (verify with `java -version`).
   * MySQL Server 8.x running locally.
   * IntelliJ IDEA (Community/Ultimate).

2. **Clone the repository**

   ```bash
   git clone https://github.com/thekhiem14/StorageManagement_CNPM.git
   ```

3. **Open in IntelliJ & set SDK**

   * Open the project folder.
   * `File → Project Structure… → Project`:

     * Project SDK: **JDK 8** (or **JDK 11**)
     * Language level: **8 - Lambdas, type annotations, etc.**

4. **Add library dependencies (local jars)**

   * `File → Project Structure… → Modules → Dependencies → + (JARs or directories)`
   * Add:

     * `Junit/junit-4.13.2.jar`
     * `Junit/hamcrest-core-1.3.jar`
     * `mysql_jdbc/mysql-connector-java-8.0.30.jar`
     * *(Optional, only if you use SQL Server)* `sqljdbc_12.6.1.0_enu/sqljdbc_12.6/enu/jars/mssql-jdbc-12.6.1.jre8.jar` or `...jre11.jar`
5. **Run the application**

   * Start from the login form: `Code/view/user/LoginFrm.java` (has `main` method or is launched by a bootstrap class).
   * Alternatively, run `ManagerHomeFrm.java` if your flow allows it.

## ✅ Testing

* Run tests directly in IntelliJ: open files under `Code/test/unit/` and click **Run**.
* JUnit 4.13.2 is already included via local jars.

## 📑 Report

Place the course report at main branch and link to it here.

[📄 View Report](https://github.com/thekhiem14/StorageManagement_CNPM/blob/main/B22DCDT171%20-%20Nguyen%20The%20Khiem%20-%20Final%20Report-7point.pdf)
