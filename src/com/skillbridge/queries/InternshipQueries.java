package com.skillbridge.queries;

public class InternshipQueries {
    public static final String INSERT = "INSERT INTO Internship (org_name, title, capacity, description, deadline) VALUES (?, ?, ?, ?, ?)";
    public static final String GET_ALL = "SELECT * FROM Internship";
    public static final String GET_BY_ID = "SELECT * FROM Internship WHERE internship_id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM Internship WHERE internship_id = ?";
}

