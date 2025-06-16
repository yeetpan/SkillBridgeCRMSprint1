package com.skillbridge.services;

import com.skillbridge.DAO.MentorDAO;
import com.skillbridge.DAO.SessionDAO;
import com.skillbridge.DAO.SessionSlotDAO;
import com.skillbridge.DAO.StudentDAO;
import com.skillbridge.entities.Session;
import com.skillbridge.entities.SessionSlot;
import com.skillbridge.util.DB;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SessionBookingService {
    private static final int MAX_QUEUE_SIZE = 20;
    private static final String LOG_FILE = "session_log.txt";

    public static synchronized String bookSession(int studentId, int mentorId, int slotId) throws SQLException {
        // Step 1: Simulate queue using DB count
        int currentBookingCount = getTotalBookingCount(slotId);
        if (currentBookingCount >= MAX_QUEUE_SIZE) {
            return "Booking queue is full .";
        }

        // Step 2: Check slot validity using Java Collections
        List<SessionSlot> availableSlots = SessionSlotDAO.getAvailableByMentor(mentorId);
        boolean isSlotAvailable = availableSlots.stream().anyMatch(slot -> slot.getSlotId() == slotId);
        if (!isSlotAvailable) {
            return "Slot ID " + slotId + " is not available for Mentor ID " + mentorId + ".";
        }

        if (SessionDAO.isAlreadyBooked(studentId, slotId)) {
            return " You have already booked this session slot.";
        }

        // Step 3: Create session
        Session session = new Session();
        session.setSlot_id(slotId);
        session.setStudent_id(studentId);
        session.setMentor_id(mentorId);
        session.setBooking_status("Scheduled");

        try {
            SessionDAO.createSession(session);
        } catch (Exception e) {
            return " Error while booking session: " + e.getMessage();
        }
        String studentName=StudentDAO.GetStudentName(studentId);
        String mentorName= MentorDAO.GetMentorName(mentorId);
        // Step 4: Log
        logToFile("Student " + studentName + " booked slot " + slotId + " with Mentor " + mentorName);
        return " Session booked for Student ID " + studentId + " with Mentor ID " + mentorId;
    }

    private static int getTotalBookingCount(int slotId) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Session WHERE slot_id = ? ";

        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1,slotId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println(" Error counting bookings: " + e.getMessage());
        }

        return count;
    }

    private static void logToFile(String log) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(log + "\n");
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }
}
