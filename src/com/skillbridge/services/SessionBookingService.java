package com.skillbridge.services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.LinkedList;
import java.util.Queue;

//for now email is considered ID can also be taken.

public class SessionBookingService {
    private static final int MAX_QUEUE_SIZE = 20;
    private static final Duration EXPIRY_DURATION = Duration.ofHours(2);
    private static final String LOG_FILE = "session_log.txt";

    // Internal class to store booking data
    private static class Booking {
        String studentEmail;
        String mentorEmail;
        LocalDateTime timestamp;

        Booking(String studentEmail, String mentorEmail, LocalDateTime timestamp) {
            this.studentEmail = studentEmail;
            this.mentorEmail = mentorEmail;
            this.timestamp = timestamp;
        }
    }

    private static final Queue<Booking> bookingQueue = new LinkedList<>();

    //synchronized used for Date and Time related operations.
    public static synchronized String bookSession(String studentEmail, String mentorEmail) {
        LocalDateTime now = LocalDateTime.now();
        cleanExpired(now);

        if (bookingQueue.size() >= MAX_QUEUE_SIZE) {
            return "Booking queue is full. Please try again later.";
        }

        Booking booking = new Booking(studentEmail, mentorEmail, now);
        bookingQueue.offer(booking);
        logToFile(booking);

        return  studentEmail + " booked a session with " + mentorEmail + ".";
    }

    private static void cleanExpired(LocalDateTime now) {
        while (!bookingQueue.isEmpty()) {
            Booking b = bookingQueue.peek();
            if (Duration.between(b.timestamp, now).compareTo(EXPIRY_DURATION) > 0) {
                bookingQueue.poll();
            } else {
                break;
            }
        }
    }

    private static void logToFile(Booking booking) {
        String log = "[" + booking.timestamp + "] " + booking.studentEmail + " registered for session of " + booking.mentorEmail + "\n";
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(log);
        } catch (IOException e) {
            System.err.println(" Logging failed: " + e.getMessage());
        }
    }
}
