package com.skillbridge.DAO;

import com.skillbridge.entities.Session;
import com.skillbridge.queries.SessionQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class SessionDAO {

    public static void createSession(Session session) {
        try {
            Connection con = DB.connect();
            String query = SessionQueries.INSERT;
            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, session.getSlot_id());
            preparedStatement.setInt(2, session.getStudent_id());
            preparedStatement.setString(3, session.getBooking_status());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Session booked successfully!!");
            }

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                session.setBooking_id(rs.getInt(1));
            }
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Session> getSessionsByStudent(int studentId) throws SQLException {
        ArrayList<Session> sessionList = new ArrayList<>();
        Connection con = DB.connect();
        String query = SessionQueries.GET_BY_STUDENT;
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, studentId);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Session session = new Session(
                    rs.getInt("slot_id"),
                    rs.getInt("student_id"),
                    rs.getString("booking_status")
            );
            session.setBooking_id(rs.getInt("booking_id"));
            sessionList.add(session);
        }
        preparedStatement.close();
        return sessionList;
    }

    public static void UpdateSessionStatus(int bookingId, String bookingStatus) {
        try {
            Connection con = DB.connect();
            String query = SessionQueries.UPDATE_STATUS;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, bookingStatus);
            preparedStatement.setInt(2, bookingId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Session status updated successfully!!");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Helper method to get session by booking ID
    public static Session getSessionByBookingId(int bookingId) throws SQLException {
        Connection con = DB.connect();
        String query = "SELECT * FROM Session WHERE booking_id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, bookingId);
        ResultSet rs = preparedStatement.executeQuery();

        Session session = null;
        if (rs.next()) {
            session = new Session(
                    rs.getInt("slot_id"),
                    rs.getInt("student_id"),
                    rs.getString("booking_status")
            );
            session.setBooking_id(rs.getInt("booking_id"));
        }
        preparedStatement.close();
        return session;
    }

    // Helper method to cancel a session
    public static void cancelSession(int bookingId) {
        UpdateSessionStatus(bookingId, "Cancelled");
    }

    // Helper method to complete a session
    public static void completeSession(int bookingId) {
        UpdateSessionStatus(bookingId, "Completed");
    }
}
