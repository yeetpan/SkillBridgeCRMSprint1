        CREATE DATABASE skillbridge;
        USE skillbridge;

        -- -----------------------------------------------
        -- 1. Table: Interests
        -- Stores all possible fields of expertise or interest
        -- -----------------------------------------------
        CREATE TABLE Interests (
            interest_id INT AUTO_INCREMENT PRIMARY KEY,
            interest_name VARCHAR(100) UNIQUE NOT NULL
        );

        -- Interests
        INSERT INTO Interests (interest_name) VALUES
        ('AI/ML'),
        ('Web Development'),
        ('Cybersecurity'),
        ('Data Science'),
        ('Cloud Computing'),
        ('DevOps'),
        ('Mobile App Development'),
        ('Blockchain'),
        ('Internet of Things'),
        ('Augmented Reality'),
        ('Virtual Reality'),
        ('Game Development'),
        ('Big Data'),
        ('Robotics'),
        ('Quantum Computing'),
        ('UI/UX Design'),
        ('Network Administration'),
        ('Database Management'),
        ('Software Testing');

        -- -----------------------------------------------
        -- 2. Table: Student
        -- Stores basic student information
        -- -----------------------------------------------
        CREATE TABLE Student (
            student_id INT AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(100) NOT NULL,
            email VARCHAR(100) UNIQUE NOT NULL,
            college VARCHAR(100)
        );

        -- Sample students
        INSERT INTO Student (name, email, college) VALUES
        ('Aarav Mehta', 'aarav@student.com', 'IIT Delhi'),
        ('Sanya Verma', 'sanya@student.com', 'BITS Pilani'),
        ('Rohan Desai', 'rohan@student.com', 'IIT Bombay'),
        ('Priya Nair', 'priya@student.com', 'IIIT Hyderabad'),
        ('Kunal Kapoor', 'kunal@student.com', 'NIT Trichy'),
        ('Neha Sharma', 'neha@student.com', 'IIT Kanpur'),
        ('Varun Joshi', 'varun@student.com', 'IIT Madras'),
        ('Ananya Singh', 'ananya@student.com', 'IIT Roorkee'),
        ('Rahul Yadav', 'rahul@student.com', 'IIT Guwahati'),
        ('Tanya Batra', 'tanya@student.com', 'VIT Vellore'),
        ('Dev Patel', 'dev@student.com', 'MIT Manipal'),
        ('Ishita Roy', 'ishita@student.com', 'SRM University'),
        ('Yash Malhotra', 'yash@student.com', 'Amity University'),
        ('Sneha Iyer', 'sneha@student.com', 'Shiv Nadar University'),
        ('Manav Rao', 'manav@student.com', 'Delhi University');

        -- -----------------------------------------------
        -- 3. Table: Student_Interests
        -- Many-to-many mapping between students and interests
        -- -----------------------------------------------
        CREATE TABLE Student_Interests (
            student_id INT,
            interest_id INT,
            PRIMARY KEY (student_id, interest_id),
            FOREIGN KEY (student_id) REFERENCES Student(student_id),
            FOREIGN KEY (interest_id) REFERENCES Interests(interest_id)
        );

        -- Map students to interests
       INSERT INTO Student_Interests VALUES
       (1, 1), (1, 4),
       (2, 2),
       (3, 1), (3, 6),
       (4, 4),
       (5, 7),
       (6, 8),
       (7, 1),
       (8, 13),
       (9, 2), (9, 7),
       (10, 6),
       (11, 1),
       (12, 4),
       (13, 13),
       (14, 6),
       (15, 8);


        -- -----------------------------------------------
        -- 4. Table: Mentor
        -- Stores mentor info including their expertise
        -- -----------------------------------------------
        CREATE TABLE Mentor (
            mentor_id INT AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(100) NOT NULL,
            email VARCHAR(100) UNIQUE NOT NULL,
            expertise_id INT NOT NULL,
            FOREIGN KEY (expertise_id) REFERENCES Interests(interest_id)
        );

        -- Sample mentors
        INSERT INTO Mentor (name, email, expertise_id) VALUES
        ('Suresh Kumar', 'suresh@100xdev.org', 1),
        ('Megha Rao', 'megha@tuf.org', 2),
        ('Rajiv Menon', 'rajiv@learnerbro.org', 4),
        ('Nikita Jain', 'nikita@freecodecamp.org', 6),
        ('Aditya Bansal', 'aditya@nextwave.org', 7),
        ('Ritika Shah', 'ritika@jspiders.org', 8),
        ('Arun Prakash', 'arun@qspiders.org', 13);
        -- -----------------------------------------------
        --Internal Table
       CREATE TABLE Matchmaking (
           matchmaking_id INT AUTO_INCREMENT PRIMARY KEY,
           student_id INT NOT NULL,
           mentor_id INT NOT NULL,
           interest_id INT NOT NULL,
           mentor_name VARCHAR(100),
           interest_name VARCHAR(100),
           FOREIGN KEY (student_id) REFERENCES Student(student_id),
           FOREIGN KEY (mentor_id) REFERENCES Mentor(mentor_id),
           FOREIGN KEY (interest_id) REFERENCES Interests(interest_id)
       );
       -------------------------------------------------
        -- -----------------------------------------------
        -- 5. Table: Internship
        -- Stores internship listings linked to a mentor
        -- -----------------------------------------------
        CREATE TABLE Internship (
            internship_id INT AUTO_INCREMENT PRIMARY KEY,
            org_name TEXT NOT NULL,
            title VARCHAR(100) NOT NULL,
            capacity INT NOT NULL,
            description TEXT,
            deadline DATE
        );

        -- Sample internships
        INSERT INTO Internship (org_name, title, capacity, description, deadline) VALUES
        ('Sarvam.AI', 'AI Intern', 3, 'Work with AI/ML team', '2025-07-15'),
        ('AccternityUI', 'Frontend Intern', 2, 'Work on React/JS', '2025-07-10'),
        ('DigiCloud', 'Cloud Ops Intern', 2, 'Cloud DevOps training', '2025-07-20'),
        ('ZettaChain', 'Blockchain R&D', 2, 'Smart contracts and chains', '2025-07-12'),
        ('DatamineX', 'Big Data Intern', 3, 'Work with Hadoop/Spark', '2025-07-22');

        -- -----------------------------------------------
        -- 6. Table: Application
        -- Tracks internship applications by students
        -- -----------------------------------------------
        CREATE TABLE Application (
            application_id INT AUTO_INCREMENT PRIMARY KEY,
            student_id INT NOT NULL,
            internship_id INT NOT NULL,
            status ENUM('Applied', 'Accepted', 'Rejected') NOT NULL,
            applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            FOREIGN KEY (student_id) REFERENCES Student(student_id),
            FOREIGN KEY (internship_id) REFERENCES Internship(internship_id)
        );

        -- Sample applications
        INSERT INTO Application (student_id, internship_id, status) VALUES
        (1, 1, 'Applied'),
        (2, 2, 'Applied'),
        (3, 3, 'Applied'),
        (4, 1, 'Applied'),
        (5, 2, 'Applied'),
        (6, 4, 'Applied'),
        (7, 1, 'Applied');

        -- -----------------------------------------------
        -- 7. Table: Session_Slot
        -- Defines available session slots offered by mentors
        -- -----------------------------------------------
        CREATE TABLE Session_Slot (
            slot_id INT AUTO_INCREMENT PRIMARY KEY,
            mentor_id INT NOT NULL,
            date DATE NOT NULL,
            time TIME NOT NULL,
            duration INT NOT NULL,  -- in minutes
            status ENUM('Available', 'Booked', 'Completed', 'Cancelled') NOT NULL,
            FOREIGN KEY (mentor_id) REFERENCES Mentor(mentor_id)
        );

        -- Sample slots
        INSERT INTO Session_Slot (mentor_id, date, time, duration, status) VALUES
       (1, '2025-06-10', '10:00:00', 60, 'Available'),
       (2, '2025-06-11', '11:00:00', 45, 'Available'),
       (3, '2025-06-12', '14:00:00', 30, 'Available'),
       (4, '2025-06-13', '15:30:00', 60, 'Available'),
       (5, '2025-06-14', '16:00:00', 45, 'Available'),
       (6, '2025-06-15', '09:30:00', 60, 'Available'),
       (7, '2025-06-16', '13:00:00', 60, 'Available');

        -- -----------------------------------------------
        -- 8. Table: Session
        -- Links a student to a booked session slot
        -- -----------------------------------------------
        CREATE TABLE Session (
            booking_id INT AUTO_INCREMENT PRIMARY KEY,
            slot_id INT NOT NULL,
            student_id INT NOT NULL,
            mentor_id INT NOT NULL,
            booking_status ENUM('Scheduled', 'Completed', 'Cancelled') NOT NULL,
            FOREIGN KEY (slot_id) REFERENCES Session_Slot(slot_id),
            FOREIGN KEY (student_id) REFERENCES Student(student_id),
            FOREIGN KEY(mentor_id) REFERENCES Mentor(mentor_id)
        );
'""""""
        -- -----------------------------------------------
        -- 9. Table: Feedback
        -- Stores student feedback after a session
        -- -----------------------------------------------
        CREATE TABLE Feedback (
            feedback_id INT AUTO_INCREMENT PRIMARY KEY,
            booking_id INT NOT NULL,
            student_id INT,
            rating INT NOT NULL,
            comments TEXT,
            FOREIGN KEY (booking_id) REFERENCES Session(booking_id),
            FOREIGN KEY (student_id) REFERENCES Student(student_id),
            CONSTRAINT check_rating CHECK (rating BETWEEN 1 AND 5)
        );

