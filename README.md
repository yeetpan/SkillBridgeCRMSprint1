# SkillBridge CRM Console App (Java + JDBC with MySQL 9.0)

**SkillBridge** is a Java-based console application designed to connect students with mentors, enable internship applications, and manage mentoring sessions effectively. It supports seamless registration, application, booking, and tracking processes via an interactive, menu-driven interface. This version uses **Java + JDBC** and Oracle XE and is organized using a structured package layout under `com.skillbridge.*`.

---
## Important note
**Please run `populate.sql` before running the java classes becase Interests are pre-loaded due to data constraints and sequence and triggers are used to avoid failures.**

## Table of Contents

* [Features](#features)
* [Tech Stack](#tech-stack)
* [Demo Video](#demo-video)
* [Installation](#installation)
* [Usage Guide](#usage-guide)
* [Main Function Walkthrough](#main-function-walkthrough)
* [Compilation Instructions](#compilation-instructions)
* [Database Setup](#database-setup)
* [Folder Summary](#folder-summary)
* [Dependencies](#dependencies)
* [Configuration](#configuration)
* [Troubleshooting](#troubleshooting)
* [Contributors](#contributors)

---

## Features

* Student and mentor registration
* Internship browsing and application
* Mentor-student matchmaking
* Session slot creation and booking
* Feedback and rating mechanism
* Real-time internship tracking
* Robust exception handling

---

## Tech Stack

* Java 17+
* Oracle Database XE (21c or 18c)
* MySQL Workbench (to manage DB & run `populate.sql`)
* MySQL JDBC Driver (`mysql-9.0.3-connector.jar`)

---


## Demo Video

[![Watch the demo](https://img.youtube.com/vi/w2smXvJxYaE/0.jpg)](https://www.youtube.com/watch?v=w2smXvJxYaE)

---

## Installation

### Prerequisites

* Java JDK 17+
* MySQL 9.0 (and its connector)
* IDE (e.g., IntelliJ IDEA, Eclipse)


### Setup Steps

```bash
# 1. Clone the repository
git clone https://github.com/shivrajkadam19/skillbridge.git
cd skillbridge

# 2. Set up the database
# Import populate.sql into your Oracle XE database

# 3. Configure database credentials
# Update /util/DB.java with your DB username and password

# 4. Compile the project
javac -d bin src/com/skillbridge/**/*.java

# 5. Run the application
java com.skillbridge.App
```

---

## Usage Guide

Once the application is launched, the following main menu is displayed:

```
Welcome to SkillBridge
1. Student
2. Mentor
3. View Internships
4. Register as Student
5. Register as Mentor
6. Exit
```

### Select Options:

* Type a number (1–6) to navigate through various functions.
* Example: Type `4` to register as a new student.

---

## Main Function Walkthrough

### Entry Point – `App.java`

* Starts a continuous loop displaying the main menu.
* Actions based on user selection:

### 1. Student Dashboard

* Input: Email
* Validates student
* Options:

  1. View My Applications
  2. Apply for Internship
  3. Book a Session
  4. View My Sessions
  5. Give Feedback
  6. View My Feedback
  7. Track My Internships
  8. Back to Main Menu

### 2. Mentor Dashboard

* Input: Email
* Validates mentor
* Options:

  1. View My Session Slots
  2. Add Slot
  3. View Feedback
  4. Back to Main Menu

### 3. View Internships

* Lists all available internships using `InternshipDAO.readInternship()`

### 4. Register as Student

* Inputs: Name, Email, College
* Displays areas of interest
* Matches with relevant mentors
* Optionally book a session or apply for internships

### 5. Register as Mentor

* Inputs: Name, Email, Expertise
* Optionally create initial session slots

### 6. Exit

* Gracefully terminates the application

---

## Compilation Instructions

To compile manually using the command line:

### 1. Compile DB Connection Utility

```bash
javac com/skillbridge/util/DB.java
```

### 2. Compile Entities

```bash
javac com/skillbridge/entities/*.java
```

### 3. Compile SQL Query Classes

```bash
javac com/skillbridge/queries/*.java
```

### 4. Compile DAOs

```bash
javac com/skillbridge/DAO/*.java
```

### 5. Compile Services

```bash
javac com/skillbridge/services/*.java
```

### 6. Compile and Run App

```bash
javac com/skillbridge/App.java
java com.skillbridge.App
```

---

## Database Setup

Run the `populate.sql` file using MySQL Workbench or any MySQL-compatible SQL CLI tool to:

* Create all tables
* Define sequences and triggers for auto-generating primary keys
* Insert sample data for Students, Mentors, Interests, Internships, and Session\_Slot

---

## Folder Summary

| Folder      | Purpose                                                        |
| ----------- | -------------------------------------------------------------- |
| `util/`     | DB connection helper using Oracle JDBC (`ojdbc`)               |
| `entities/` | POJO classes representing each table (Student, Mentor, etc.)   |
| `queries/`  | SQL strings for each entity's operations                       |
| `DAO/`      | Handles actual SQL interactions using `PreparedStatement`      |
| `services/` | Contains business logic for workflows like booking or matching |
| `App.java`  | Entry point and menu for interacting with the console system   |

---

## Dependencies

* `java.sql.*` – Database interactions
* `java.util.*` – Handling lists, scanners, and exceptions
* DAO Classes – `StudentDAO`, `MentorDAO`, `InternshipDAO`, etc.
* Service Classes – `InternshipTrackingService`, etc.
* Entity Classes – `Student`, `Mentor`, `Application`, etc.
* Utility – `DB`, `EmailValidate`
* Custom Exceptions – `InvalidMenuChoiceException`, `InvalidRatingException`, etc.

---

## Configuration

Ensure the following configurations are correct:

* Update database credentials inside `com.skillbridge.util.DB.java`
* Make sure tables exist and are populated as per `populate.sql`

Tables used:

* `STUDENTS`, `MENTORS`, `INTERESTS`, `INTERNSHIPS`, `SESSION_SLOT`, `STU_SESSION`, `FEEDBACK`, `APPLICATION`, `MATCHMAKING`

---

## Troubleshooting

| Issue                        | Fix                                                 |
| ---------------------------- | --------------------------------------------------- |
| `SQLException`               | Verify DB setup, schema, and connection credentials |
| `StudentNotFoundException`   | Ensure valid and registered student email           |
| `InvalidMenuChoiceException` | Input only numeric values in the correct range      |
| `InvalidRatingException`     | Ratings must be between 1–5                         |
| `SlotUnavailableException`   | Mentor has no available slots                       |

---

## Contributors

* Swayam
* Tanishq
* Shivraj
* Samyukta
* Rujal
* Teja
---
