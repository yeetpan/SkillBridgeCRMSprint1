//FeedbackDAO
package com.skillbridge.DAO;

import com.skillbridge.entities.Feedback;
import com.skillbridge.queries.FeedBackQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class FeedbackDAO {

    public static void createFeedback(Feedback feedback) {
        try {
            Connection con = DB.connect();
            String query = FeedBackQueries.INSERT;
            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, feedback.getBookingId());

            // Handle nullable student_id
            if (feedback.getStudentId() != null) {
                preparedStatement.setInt(2, feedback.getStudentId());
            } else {
                preparedStatement.setNull(2, Types.INTEGER);
            }

            preparedStatement.setInt(3, feedback.getRating());
            preparedStatement.setString(4, feedback.getComments());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Feedback inserted successfully!!");
            }

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                feedback.setFeedbackId(rs.getInt(1));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Feedback> getFeedbackByStudent(int studentId) throws SQLException {
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        Connection con = DB.connect();
        String query = FeedBackQueries.GET_BY_STUDENT;
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, studentId);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Feedback feedback = new Feedback();
            feedback.setFeedbackId(rs.getInt("feedback_id"));
            feedback.setBookingId(rs.getInt("booking_id"));

            // Handle nullable student_id
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
        preparedStatement.close();
        return feedbackList;
    }

    public static void UpdateFeedback(int feedbackId, int rating, String comments) {
        try {
            Connection con = DB.connect();
            String query = FeedBackQueries.UPDATE;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, rating);
            preparedStatement.setString(2, comments);
            preparedStatement.setInt(3, feedbackId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Feedback updated successfully!!");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void DeleteFeedback(int feedbackId) {
        try {
            Connection con = DB.connect();
            String query = FeedBackQueries.DELETE;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, feedbackId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted Feedback with id " + feedbackId + " successfully!!");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Feedback> getFeedbackByMentor(int mentorId) throws SQLException {
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        Connection con = DB.connect();
        String query = FeedBackQueries.VIEW_FEEDBACK_BY_MENTORS;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, mentorId);
        ResultSet rs = ps.executeQuery();

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

        ps.close();
        return feedbackList;
    }

}
