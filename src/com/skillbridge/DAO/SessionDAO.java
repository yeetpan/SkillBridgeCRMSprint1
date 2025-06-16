package com.skillbridge.DAO;

import com.skillbridge.entities.Session;
import com.skillbridge.queries.SessionQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class SessionDAO {

    public static void createSession(Session session) throws SQLException {
        try (Connection con = DB.connect();
             PreparedStatement preparedStatement = con.prepareStatement(SessionQueries.INSERT, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, session.getSlot_id());
            preparedStatement.setInt(2, session.getStudent_id());
            preparedStatement.setInt(3, session.getMentor_id());
            preparedStatement.setString(4, session.getBooking_status());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Session booked successfully!!");
            }

            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if(rs.next()){
                    session.setBooking_id(rs.getInt(1));
                }
                } catch (SQLException e) {
                System.err.println(" Could not fetch booking_id: " + e.getMessage());
            }
        }
    }

    public static ArrayList<Session> getSessionsByStudent(int studentId) throws SQLException {
        ArrayList<Session> sessionList = new ArrayList<>();
        try (Connection con = DB.connect();
             PreparedStatement preparedStatement = con.prepareStatement(SessionQueries.GET_BY_STUDENT)) {
            preparedStatement.setInt(1, studentId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Session session = new Session(
                            rs.getInt("slot_id"),
                            rs.getInt("student_id"),
                            rs.getInt("mentor_id"),
                            rs.getString("booking_status")
                    );
                    session.setBooking_id(rs.getInt("booking_id"));
                    sessionList.add(session);
                }
            }
        }
        return sessionList;
    }

    public static void UpdateSessionStatus(int bookingId, String bookingStatus) throws SQLException {
        try (Connection con = DB.connect();
             PreparedStatement preparedStatement = con.prepareStatement(SessionQueries.UPDATE_STATUS)) {
            // Standardize case for database consistency
            String normalizedStatus = bookingStatus;
            if (bookingStatus.equalsIgnoreCase("cancelled")) {
                normalizedStatus = "Cancelled";
            } else if (bookingStatus.equalsIgnoreCase("completed")) {
                normalizedStatus = "Completed";
            }
            preparedStatement.setString(1, normalizedStatus);
            preparedStatement.setInt(2, bookingId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Session status updated successfully!!");
            }
        }
    }

    public static ArrayList<Session> getSessionsByBookingId(int bookingId) throws SQLException {
        ArrayList<Session> sessions = new ArrayList<>();

        try (Connection con = DB.connect();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Session WHERE booking_id = ?")) {

            preparedStatement.setInt(1, bookingId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Session session = new Session(
                            rs.getInt("slot_id"),
                            rs.getInt("student_id"),
                            rs.getInt("mentor_id"),
                            rs.getString("booking_status")
                    );
                    session.setBooking_id(rs.getInt("booking_id"));
                    sessions.add(session);
                }
            }
        }

        return sessions;
    }

    public static boolean isAlreadyBooked(int studentId, int slotId) {
        String query = "SELECT COUNT(*) FROM Session WHERE student_id = ? AND slot_id = ?";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ps.setInt(2, slotId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking existing booking: " + e.getMessage());
        }
        return false;
    }


}