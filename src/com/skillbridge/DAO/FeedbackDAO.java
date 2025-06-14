package com.skillbridge.DAO;

import com.skillbridge.entities.Feedback;
import com.skillbridge.queries.FeedBackQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class FeedbackDAO {

    public static void createFeedback(Feedback feedback) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to create feedback.");
            return;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(FeedBackQueries.INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, feedback.getBookingId());

            if (feedback.getStudentId() != null) {
                ps.setInt(2, feedback.getStudentId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setInt(3, feedback.getRating());
            ps.setString(4, feedback.getComments());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Feedback inserted successfully.");
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    feedback.setFeedbackId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [createFeedback]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }
    }

    public static ArrayList<Feedback> getFeedbackByStudent(int studentId) {
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to fetch feedback.");
            return feedbackList;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(FeedBackQueries.GET_BY_STUDENT);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackId(rs.getInt("feedback_id"));
                feedback.setBookingId(rs.getInt("booking_id"));

                int studentIdFromDb = rs.getInt("student_id");
                if (rs.wasNull()) {
                    feedback.setStudentId(null);
                } else {
                    feedback.setStudentId(studentIdFromDb);
                }

                feedback.setRating(rs.getInt("rating"));
                feedback.setComments(rs.getString("comments"));
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [getFeedbackByStudent]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }

        return feedbackList;
    }

    public static void updateFeedback(int feedbackId, int rating, String comments) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to update feedback.");
            return;
        }

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(FeedBackQueries.UPDATE);
            ps.setInt(1, rating);
            ps.setString(2, comments);
            ps.setInt(3, feedbackId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Feedback updated successfully.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [updateFeedback]: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }
    }

    public static void deleteFeedback(int feedbackId) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to delete feedback.");
            return;
        }

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(FeedBackQueries.DELETE);
            ps.setInt(1, feedbackId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("🗑️ Feedback deleted with ID: " + feedbackId);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [deleteFeedback]: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }
    }
}
