package com.skillbridge.queries;

public class ApplicationQueries {
    public static final String INSERT = "INSERT INTO Application (student_id, internship_id, status) VALUES (?, ?, ?)";
    public static final String GET_BY_STUDENT = "SELECT * FROM Application WHERE student_id = ?";
    public static final String GET_BY_INTERNSHIP = "SELECT * FROM Application WHERE internship_id = ?";
    public static final String UPDATE_STATUS = "UPDATE Application SET status = ? WHERE application_id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM Application WHERE application_id = ?";
    public static final String GET_ALL = "SELECT * FROM Application";
}
