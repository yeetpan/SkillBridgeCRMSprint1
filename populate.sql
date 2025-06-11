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
       (1, 1), (1, 4),     -- Aarav: AI/ML, Data Science
       (2, 2),             -- Sanya: Web Dev
       (3, 1), (3, 6),     -- Rohan: AI/ML, DevOps
       (4, 4),             -- Priya: Data Science
       (5, 7),             -- Kunal: Mobile App
       (6, 8),             -- Neha: Blockchain
       (7, 1),             -- Varun: AI/ML
       (8, 13),            -- Ananya: Big Data
       (9, 2), (9, 7),     -- Rahul: Web Dev, Mobile App
       (10, 6),            -- Tanya: DevOps
       (11, 1),            -- Dev: AI/ML
       (12, 4),            -- Ishita: Data Science
       (13, 13),           -- Yash: Big Data
       (14, 6),            -- Sneha: DevOps
       (15, 8);            -- Manav: Blockchain


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
        ('Suresh Kumar', 'rakesh@100xdev.org', 1), -- AI/ML
        ('Megha Rao', 'megha@tuf.org', 2);         -- Web Dev
        ('Rajiv Menon', 'rajiv@mentor.org', 4),    -- Data Science
        ('Nikita Jain', 'nikita@mentor.org', 6),   -- DevOps
        ('Aditya Bansal', 'aditya@mentor.org', 7), -- Mobile App
        ('Ritika Shah', 'ritika@mentor.org', 8),   -- Blockchain
        ('Arun Prakash', 'arun@mentor.org', 13);   -- Big Data
        -- -----------------------------------------------

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
        ('AccternityUI', 'Frontend Intern', 2, 'Work on React/JS', '2025-07-10');

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
        (2, 2, 'Applied');

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
        (2, '2025-06-12', '15:00:00', 45, 'Available');

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

        -- Sample booking
         INSERT INTO Session (slot_id, student_id,mentor_id, booking_status) VALUES
            (1, 1,1, 'Scheduled');

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

        -- Sample feedback
        INSERT INTO Feedback (booking_id, student_id, rating, comments) VALUES
        (1, 1,  5, 'Great session on ML basics!');