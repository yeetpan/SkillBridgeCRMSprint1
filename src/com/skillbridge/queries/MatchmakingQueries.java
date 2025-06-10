package com.skillbridge.queries;

public class MatchmakingQueries {
    public static final String GET_MENTORS_BY_STUDENT_INTERESTS = """
        SELECT DISTINCT m.* FROM Mentor m
        JOIN Student_Interests si ON m.expertise_id = si.interest_id
        WHERE si.student_id = ?
    """;
}
