package com.skillbridge.queries;

public class FeedBackQueries {
    public static final String INSERT = "INSERT INTO Feedback (booking_id, student_id, mentor_id, rating, comments) VALUES (?, ?, ?, ?, ?)";
    public static final String GET_BY_STUDENT = "SELECT * FROM Feedback WHERE student_id = ?";
    public static final String UPDATE = "UPDATE Feedback SET rating = ?, comments = ? WHERE feedback_id = ?";
    public static final String DELETE = "DELETE FROM Feedback WHERE feedback_id = ?";
}
