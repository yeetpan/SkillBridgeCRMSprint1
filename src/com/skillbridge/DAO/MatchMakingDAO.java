package com.skillbridge.DAO;

import com.skillbridge.queries.MatchmakingQueries;
import com.skillbridge.util.DB;

import java.sql.*;

public class MatchMakingDAO {

    // Called when student logs in first time
    public static void createMatchMakingForStudent(int student_id) {
        try (Connection con = DB.connect()) {

            // If already matched, skip insert
            String checkQuery = "SELECT 1 FROM MatchMaking WHERE student_id = ? LIMIT 1";
            try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, student_id);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    System.out.println(" Student already has match-making data.");
                    return;
                }
            }

            // Insert matched mentors
            try (PreparedStatement insertStmt = con.prepareStatement(MatchmakingQueries.INSERT_FOR_MATCHMAKING)) {
                insertStmt.setInt(1, student_id);
                int rowsAffected = insertStmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println(" Matchmaking done. Showing matched mentors:");
                    showMatchedMentors(student_id);
                } else {
                    System.out.println(" No mentors matched for the student.");
                }
            }

        } catch (SQLException e) {
            System.err.println(" Error during matchmaking: " + e.getMessage());
        }
    }

    // Called always to just show mentor matches
    public static void showMatchedMentors(int student_id) {
        try (Connection con = DB.connect();
             PreparedStatement stmt = con.prepareStatement(MatchmakingQueries.SHOW_MATCHMAKING)) {

            stmt.setInt(1, student_id);
            ResultSet rs = stmt.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                int mentorId = rs.getInt("mentor_id");
                String mentorName = rs.getString("mentor_name");
                String interestName = rs.getString("interest_name");

                System.out.println("Mentor ID: " + mentorId + ", Name: " + mentorName + ", Expertise: " + interestName);
            }

            if (!found) {
                System.out.println("No mentor matches found.");
            }

        } catch (SQLException e) {
            System.err.println("Error showing matched mentors: " + e.getMessage());
        }
    }
}
