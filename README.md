# SkillBridge

**SkillBridge** is a Java-based console application designed to connect students with mentors, enable internship applications, and manage mentoring sessions effectively. It supports seamless registration, application, booking, and tracking processes via an interactive, menu-driven interface.

---

## 📋 Table of Contents
- [Features](#-features)
- [Installation](#-installation)
- [Usage Guide](#-usage-guide)
- [Main Function Walkthrough](#-main-function-walkthrough)
- [Dependencies](#-dependencies)
- [Configuration](#-configuration)
- [Troubleshooting](#-troubleshooting)
- [Contributors](#-contributors)
- [License](#-license)

---

## 🚀 Features
- Student and mentor registration
- Internship browsing and application
- Mentor-student matchmaking
- Session slot creation and booking
- Feedback and rating mechanism
- Real-time internship tracking
- Robust exception handling

---

## 🛠️ Installation

### Prerequisites
- Java JDK 17+
- Maven or Gradle (for dependency management)
- Oracle XE or Oracle 21c
- IDE (e.g., IntelliJ IDEA, Eclipse)

### Setup Steps

```bash
# 1. Clone the repository
git clone https://github.com/shivrajkadam19/skillbridge.git
cd skillbridge

# 2. Set up the database
# Import /populate.sql into your MySQL database.

# 3. Configure database credentials
# Update /util/DB.java or relevant configuration class with your DB username and password.

# 4. Compile the project
javac -d bin src/com/skillbridge/**/*.java

# 5. Run the application
java com.skillbridge.App
```

---

## 🎮 Usage Guide

Once the application is launched, the following main menu is displayed:

```text
Welcome to SkillBridge
1. Student
2. Mentor
3. View Internships
4. Register as Student
5. Register as Mentor
6. Exit
```

### Select Options:
- **Type a number (1–6)** to navigate through various functions.
- **Example**: Type `4` to register as a new student.

---

## 🔍 Main Function Walkthrough

### ▶️ Entry Point – `App.java`
- Starts a continuous loop displaying the main menu.
- Actions based on user selection:

### 1. **Student Dashboard**
- Input: Email
- Validates student
- Options:
    1. View My Applications
    2. Apply for Internship
    3. Book a Session
    4. View My Sessions
    5. Give Feedback
    6. View My Feedback
    7. Track My Internships
    8. Back to Main Menu

### 2. **Mentor Dashboard**
- Input: Email
- Validates mentor
- Options:
    1. View My Session Slots
    2. Add Slot
    3. View Feedback
    4. Back to Main Menu

### 3. **View Internships**
- Lists all available internships using `InternshipDAO.readInternship()`.

### 4. **Register as Student**
- Inputs: Name, Email, College
- Displays areas of interest
- Matches with relevant mentors
- Optionally book a session or apply for internships

### 5. **Register as Mentor**
- Inputs: Name, Email, Expertise
- Optionally create initial session slots

### 6. **Exit**
- Gracefully terminates the application

---

## 📦 Dependencies

- `java.sql.*` – Database interactions
- `java.util.ArrayList` - Interaction and manipulation of response data from database
- `Scanner` – Input handling
- DAO Classes – `StudentDAO`, `MentorDAO`, `InternshipDAO`, etc.
- Service Classes – `InternshipTrackingService`, etc.
- Entity Classes – `Student`, `Mentor`, etc.
- Utilities – `EmailValidate`, `DB`, etc.
- Custom Exceptions – `InvalidMenuChoiceException`, `InvalidRatingException`, etc.

---

## ⚙️ Configuration

Make sure the following are correctly set:
- **Database connection settings** are updated in the config class (`DB.java`).
- **Required tables** exist:
    - `students`, `mentors`, `internships`, `sessions`, `feedback`, `interests`

---

## 🧪 Troubleshooting

| Issue                     | Fix                                                 |
|--------------------------|-----------------------------------------------------|
| `SQLException`           | Verify DB setup, schema, and connection credentials |
| `StudentNotFoundException` | Ensure valid and registered student email         |
| `InvalidMenuChoiceException` | Input only numeric values in the correct range |
| `InvalidRatingException` | Ratings must be between 1–5                         |
| `SlotUnavailableException` | Mentor has no available slots                     |


---

## 👥 Contributors

- **The Crew** –
    - Swayam
    - Tanishq
    - Shivraj
    - Samyukta
    - Rujal
    - Teja
---