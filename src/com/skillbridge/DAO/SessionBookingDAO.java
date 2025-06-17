package com.skillbridge.DAO;

import com.skillbridge.entities.SessionBooking;  // Updated import
import com.skillbridge.queries.SessionBookingQueries;  // Updated import
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class SessionBookingDAO {  // Renamed class

    public static void createSessionBooking(SessionBooking sessionBooking) throws SQLException {  // Updated method name and parameter
        try (Connection con = DB.connect();
             PreparedStatement preparedStatement = con.prepareStatement(SessionBookingQueries.INSERT, Statement.RETURN_GENERATED_KEYS)) {  // Updated query reference
            preparedStatement.setInt(1, sessionBooking.getSlot_id());
            preparedStatement.setInt(2, sessionBooking.getStudent_id());
            preparedStatement.setInt(3, sessionBooking.getMentor_id());
            preparedStatement.setString(4, sessionBooking.getBooking_status());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Session booked successfully!!");
            }

            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    sessionBooking.setBooking_id(rs.getInt(1));
                }
            }
        }
    }

    public static ArrayList<SessionBooking> getSessionBookingsByStudent(int studentId) throws SQLException {  // Updated method name and return type
        ArrayList<SessionBooking> sessionBookingList = new ArrayList<>();  // Updated variable type
        try (Connection con = DB.connect();
             PreparedStatement preparedStatement = con.prepareStatement(SessionBookingQueries.GET_BY_STUDENT)) {  // Updated query reference
            preparedStatement.setInt(1, studentId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    SessionBooking sessionBooking = new SessionBooking(  // Updated entity type
                            rs.getInt("slot_id"),
                            rs.getInt("student_id"),
                            rs.getInt("mentor_id"),
                            rs.getString("booking_status")
                    );
                    sessionBooking.setBooking_id(rs.getInt("booking_id"));
                    sessionBookingList.add(sessionBooking);
                }
            }
        }
        return sessionBookingList;
    }

    public static void updateSessionBookingStatus(int bookingId, String bookingStatus) throws SQLException {  // Updated method name
        try (Connection con = DB.connect();
             PreparedStatement preparedStatement = con.prepareStatement(SessionBookingQueries.UPDATE_STATUS)) {  // Updated query reference
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

    public static ArrayList<SessionBooking> getSessionBookingsByBookingId(int bookingId) throws SQLException {  // Updated method name and return type
        ArrayList<SessionBooking> sessionBookings = new ArrayList<>();  // Updated variable type

        try (Connection con = DB.connect();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Session_Booking WHERE booking_id = ?")) {  // Updated table name

            preparedStatement.setInt(1, bookingId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    SessionBooking sessionBooking = new SessionBooking(  // Updated entity type
                            rs.getInt("slot_id"),
                            rs.getInt("student_id"),
                            rs.getInt("mentor_id"),
                            rs.getString("booking_status")
                    );
                    sessionBooking.setBooking_id(rs.getInt("booking_id"));
                    sessionBookings.add(sessionBooking);
                }
            }
        }

        return sessionBookings;
    }
}

