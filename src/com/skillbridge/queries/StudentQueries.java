package com.skillbridge.queries;

public class StudentQueries {
    public static String insert="INSERT INTO Student (student_name, student_email, student_college) VALUES (?, ?, ?)";
    public static final String get_id = "SELECT student_id FROM Student WHERE student_email = ?";
    public static final String get_all = "SELECT * FROM Student";
    public static final String delete_by_id = "DELETE FROM Student WHERE student_id = ?";
    public static final String update = "UPDATE Student SET student_name = ?, student_email = ?, student_college = ? WHERE student_id = ?";
    public static final String get_name_by_id="SELECT student_name FROM Student WHERE student_id = ?";
}

