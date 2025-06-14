package com.skillbridge.services;

import com.skillbridge.DAO.SessionDAO;
import com.skillbridge.DAO.SessionSlotDAO;
import com.skillbridge.entities.Session;
import com.skillbridge.entities.SessionSlot;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SessionBookingService {
    private static final int MAX_QUEUE_SIZE = 20;
    private static final String LOG_FILE = "session_log.txt";

    private static class Booking {
        int studentId;
        int mentorId;
        LocalDateTime timestamp;

        Booking(int studentId, int mentorId, LocalDateTime timestamp) {
            this.studentId = studentId;
            this.mentorId = mentorId;
            this.timestamp = timestamp;
        }
    }

    private static final Queue<Booking> bookingQueue = new LinkedList<>();

    public static synchronized String bookSession(int studentId, int mentorId,int slotId) {
        if (bookingQueue.size() >= MAX_QUEUE_SIZE) {
            return "Booking queue is full. Please try again later.";
        }

        // Step 1: Find an available session slot for the mentor
        List<SessionSlot> availableSlots = SessionSlotDAO.getAvailableByMentor(mentorId);
        if (availableSlots.isEmpty()) {
            return "No available slots found for the selected mentor.";
        }



        // Step 2: Update slot status to 'Booked'
        SessionSlotDAO.updateStatus(slotId, "Booked");

        // Step 3: Create session and persist it
        Session session = new Session();
        session.setSlot_id(slotId);
        session.setStudent_id(studentId);
        session.setMentor_id(mentorId);
        session.setBooking_status("Pending");

        try {
            SessionDAO.createSession(session);
        } catch (Exception e) {
            return "Error while booking session: " + e.getMessage();
        }

        // Step 4: Queue and log the booking
        Booking booking = new Booking(studentId, mentorId, LocalDateTime.now());
        bookingQueue.offer(booking);
        logToFile(booking);

        return "Student ID " + studentId + " successfully booked a session with Mentor ID " + mentorId +
                " [Slot ID: " + slotId + "]";
    }

    private static void logToFile(Booking booking) {
        String log = "[" + booking.timestamp + "] Student ID " + booking.studentId +
                " booked session with Mentor ID " + booking.mentorId + "\n";
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(log);
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }
}
