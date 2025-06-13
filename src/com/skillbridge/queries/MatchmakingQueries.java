package com.skillbridge.queries;

public class MatchmakingQueries {
    public static final String GET_MENTORS_BY_STUDENT_INTERESTS = """
        SELECT DISTINCT m.* FROM Mentor m
        JOIN Student_Interests si ON m.expertise_id = si.interest_id
        WHERE si.student_id = ?
    """;
}
//SELECT s.name AS student_name, m.name AS mentor_name, i.interest_name
//FROM Student s
//JOIN Student_Interests si ON s.student_id = si.student_id
//JOIN Interests i ON si.interest_id = i.interest_id
//JOIN Mentor m ON m.expertise_id = i.interest_id;