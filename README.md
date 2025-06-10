
# SkillBridge CRM Console App (Java + JDBC)

**SkillBridge**, a nonprofit that connects students with mentors and internship opportunities. It uses **Java + JDBC** and built for Sprint-1 and using  a `com.skillbridge.*` package structure.

---


## Tech Stack

- **Java 17+**
- **MySQL 9.0+**
- **MySQLWorkbench** for url or commands can be used to get username/port.
- **JDBC(JConnector)**
---

## Getting Started

### Prerequisites

- MySQL 9.0+
- Tables created and populated (see `populate.sql` )

---

##  Compilation Instructions

To compile the project from the command line, follow this order:

### 1. Compile DB Connection Utility
```
javac com/skillbridge/util/DB.java
```

### 2. Compile Entities
```
javac com/skillbridge/entities/*.java
```

### 3. Compile SQL Query Classes
```
javac com/skillbridge/queries/*.java
```

### 4. Compile DAOs
```
javac com/skillbridge/DAO/*.java
```

### 5. Compile Services
```
javac com/skillbridge/services/*.java
```

### 6. Compile and Run App
```
javac com/skillbridge/App.java
java com.skillbridge.App
```

## Folder Summary

| Folder             | Purpose                                                              |
|--------------------|----------------------------------------------------------------------|
| `util/`            | Database connection helper (`DB.java`)                               |
| `entities/`        | POJOs representing each table (Student, Mentor, Internship, etc.)    |
| `queries/`         | Centralized SQL query strings per entity                             |
| `DAO/`             | Performs all SQL operations for each table                           |
| `services/`        | Business logic (session scheduling, internship tracking)             |
| `App.java`         | Entry point for launching the console application                    |

---

## Notes

- All primary keys use `AUTO_INCREMENT` (or can be adapted to UUID)
- All foreign key constraints are enforced for integrity
- Enum columns are used for status tracking (`status`, `booking_status`, etc.)
