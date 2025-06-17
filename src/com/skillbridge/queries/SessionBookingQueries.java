package com.skillbridge.queries;

public class SessionBookingQueries {
    public static final String INSERT = "INSERT INTO Session_Booking (slot_id, student_id, mentor_id, booking_status) VALUES (?, ?, ?, ?)";
    public static final String GET_BY_STUDENT = "SELECT * FROM Session_Booking WHERE student_id = ?";
    public static final String UPDATE_STATUS = "UPDATE Session_Booking SET booking_status = ? WHERE booking_id = ?";
}

