package com.skillbridge.DAO;

import com.skillbridge.entities.Session;
import com.skillbridge.queries.SessionQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class SessionDAO {

    // Create new session booking
    public static void createSession(Session session) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to create session.");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(SessionQueries.INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, session.getSlotId());
            ps.setInt(2, session.getStudentId());
            ps.setInt(3, session.getMentorId());
            ps.setString(4, session.getBookingStatus());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Session booked successfully.");
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        session.setBookingId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [createSession]: " + e.getMessage());
        } finally {
            DB.closeConnection(con);
        }
    }

    // Retrieve sessions by student ID
    public static ArrayList<Session> getSessionsByStudent(int studentId) {
        ArrayList<Session> sessionList = new ArrayList<>();
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to fetch sessions.");
            return sessionList;
        }

        try (PreparedStatement ps = con.prepareStatement(SessionQueries.GET_BY_STUDENT)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Session session = new Session(
                            rs.getInt("slot_id"),
                            rs.getInt("student_id"),
                            rs.getInt("mentor_id"),
                            rs.getString("booking_status")
                    );
                    session.setBookingId(rs.getInt("booking_id"));
                    sessionList.add(session);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [getSessionsByStudent]: " + e.getMessage());
        } finally {
            DB.closeConnection(con);
        }

        return sessionList;
    }

    // Update session status by booking ID
    public static void updateSessionStatus(int bookingId, String bookingStatus) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to update session status.");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(SessionQueries.UPDATE_STATUS)) {
            String normalizedStatus = bookingStatus.trim();
            if (bookingStatus.equalsIgnoreCase("cancelled")) {
                normalizedStatus = "Cancelled";
            } else if (bookingStatus.equalsIgnoreCase("completed")) {
                normalizedStatus = "Completed";
            }

            ps.setString(1, normalizedStatus);
            ps.setInt(2, bookingId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Session status updated successfully.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [updateSessionStatus]: " + e.getMessage());
        } finally {
            DB.closeConnection(con);
        }
    }

    // Retrieve session by booking ID
    public static Optional<Session> getSessionByBookingId(int bookingId) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to fetch session.");
            return Optional.empty();
        }

        try (PreparedStatement ps = con.prepareStatement(SessionQueries.GET_BY_STUDENT)) {
            ps.setInt(1, bookingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Session session = new Session(
                            rs.getInt("slot_id"),
                            rs.getInt("student_id"),
                            rs.getInt("mentor_id"),
                            rs.getString("booking_status")
                    );
                    session.setBookingId(rs.getInt("booking_id"));
                    return Optional.of(session);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [getSessionByBookingId]: " + e.getMessage());
        } finally {
            DB.closeConnection(con);
        }

        return Optional.empty();
    }
}
