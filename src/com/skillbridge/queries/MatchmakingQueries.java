package com.skillbridge.queries;

public class MatchmakingQueries {
    public static final String INSERT_FOR_MATCHMAKING = """
            INSERT INTO Matchmaking (student_id, mentor_id, interest_id,interest_name)
            SELECT
            s.student_id, m.mentor_id, i.interest_id,i.interest_name
            FROM Student s
            JOIN Student_Interests si ON s.student_id = si.student_id
            JOIN Interests i ON si.interest_id = i.interest_id
            JOIN Mentor m ON m.expertise_id = i.interest_id
            WHERE s.student_id = ?
            """;
    public static final String SHOW_MATCHMAKING="SELECT * FROM Matchmaking WHERE student_id = ?";
}
