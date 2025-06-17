package com.skillbridge.queries;

public class SessionSlotQueries {
    public static final String INSERT = "INSERT INTO Session_Slot (mentor_id, slot_date, slot_time, duration, status) VALUES (?, ?, ?, ?, ?)";
    public static final String GET_AVAILABLE_BY_MENTOR = "SELECT * FROM Session_Slot WHERE mentor_id = ? AND status = 'Available'";
    public static final String UPDATE_STATUS = "UPDATE Session_Slot SET status = ? WHERE slot_id = ?";
}

