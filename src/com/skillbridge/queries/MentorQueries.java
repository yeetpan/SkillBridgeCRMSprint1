package com.skillbridge.queries;

public class MentorQueries {
    //string queries to not fill app code.
    public static final String insert = "INSERT INTO Mentor (name, email, expertise_id) VALUES (?, ?, ?)";
    public static final String get_all = "SELECT * FROM Mentor";
    public static final String get_by_expertise = "SELECT * FROM Mentor WHERE expertise_id = ?";
    public static final String select_id_by_email = "SELECT * FROM Mentor WHERE email = ?";
    public static final String DELETE_BY_ID = "DELETE FROM Mentor WHERE mentor_id = ?";
    public static final String UPDATE = "UPDATE Mentor SET name = ?, email = ?, expertise_id = ? WHERE mentor_id = ?";
}
