package com.skillbridge.queries;

public class MentorAvailabilityQueries {

    public static final String INSERT = "INSERT INTO Mentor_Availability (mentor_id, date, start_time, end_time) VALUES (?, ?, ?, ?)";
    public static final String GET_BY_MENTOR = "SELECT * FROM Mentor_Availability WHERE mentor_id = ?";
    public static final String GET_ALL_AVAILABLE_DATES = """
        SELECT * FROM Mentor_Availability 
        WHERE mentor_id = ? 
        ORDER BY date
    """;
}
