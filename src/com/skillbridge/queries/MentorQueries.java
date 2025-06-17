package com.skillbridge.queries;

public class MentorQueries {
    public static final String insert = "INSERT INTO Mentor (mentor_name, mentor_email, expertise_id) VALUES (?, ?, ?)";
    public static final String get_all = "SELECT * FROM Mentor";
    public static final String get_by_expertise = "SELECT * FROM Mentor WHERE expertise_id = ?";
    public static final String select_id_by_email = "SELECT * FROM Mentor WHERE mentor_email = ?";
    public static final String DELETE_BY_ID = "DELETE FROM Mentor WHERE mentor_id = ?";
    public static final String UPDATE = "UPDATE Mentor SET mentor_name = ?, mentor_email = ?, expertise_id = ? WHERE mentor_id = ?";
}
