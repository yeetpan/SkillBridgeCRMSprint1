package com.skillbridge.queries;

public class FeedBackQueries {
    public static final String INSERT = "INSERT INTO Feedback (booking_id, student_id, rating, comments) VALUES (?, ?, ?, ?)";
    public static final String GET_BY_STUDENT = "SELECT * FROM Feedback WHERE student_id = ?";
    public static final String UPDATE = "UPDATE Feedback SET rating = ?, comments = ? WHERE feedback_id = ?";
    public static final String VIEW_FEEDBACK_BY_MENTORS = "SELECT f.feedback_id, f.rating, f.comments, f.student_id, s.booking_id FROM Feedback f JOIN Session s ON f.booking_id = s.booking_id WHERE s.mentor_id = ?";
    public static final String DELETE = "DELETE FROM Feedback WHERE feedback_id = ?";
}
