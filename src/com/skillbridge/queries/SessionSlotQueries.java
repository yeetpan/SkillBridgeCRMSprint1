package com.skillbridge.queries;

public class SessionSlotQueries {
    public static final String INSERT = "INSERT INTO Mentor_Session_Slot (mentor_id, date, time, duration, status) VALUES (?, ?, ?, ?, ?)";
    public static final String GET_AVAILABLE_BY_MENTOR = "SELECT * FROM Mentor_Session_Slot WHERE mentor_id = ? AND status = 'Available'";
    public static final String UPDATE_STATUS = "UPDATE Mentor_Session_Slot SET status = ? WHERE slot_id = ?";

}
