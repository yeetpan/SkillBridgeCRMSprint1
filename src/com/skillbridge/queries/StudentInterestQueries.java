package com.skillbridge.queries;

// will be displaying Interests table to the student,mentor and by that basis the user will be selecting.
public class StudentInterestQueries {
    public static final String INSERT = "INSERT INTO Student_Interests (student_id, interest_id) VALUES (?, ?)";
    public static final String GET_BY_STUDENT = "SELECT interest_id FROM Student_Interests WHERE student_id = ?";
    public static final String DELETE_BY_STUDENT = "DELETE FROM Student_Interests WHERE student_id = ?";
}
