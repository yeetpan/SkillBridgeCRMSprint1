package com.skillbridge.DAO;

import com.skillbridge.queries.MatchmakingQueries;
import com.skillbridge.util.DB;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class MatchMakingDAO {
    public static void MatchMaker(int student_id) {
        try {
            Connection con = DB.connect();

            PreparedStatement insertStmt = con.prepareStatement(MatchmakingQueries.INSERT_FOR_MATCHMAKING);
            insertStmt.setInt(1, student_id);
            int rowsAffected = insertStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Matchmaking Done! Showing Mentors!!");

                // Now show the matched mentors
                PreparedStatement showStmt = con.prepareStatement(MatchmakingQueries.SHOW_MATCHMAKING);
                showStmt.setInt(1, student_id);

                ResultSet rs = showStmt.executeQuery();
                while (rs.next()) {
                    int mentorId = rs.getInt("mentor_id");
                    String mentorName = rs.getString("mentor_name");
                    String interestName = rs.getString("interest_name");
                    System.out.println("Mentor ID: " + mentorId + ", Name: " + mentorName + ", Expertise: " + interestName);
                }
                rs.close();
                showStmt.close();
            } else {
                System.out.println("No mentors matched for the given student.");
            }

            insertStmt.close();
            con.close();

        } catch (SQLException e) {
            System.err.println("Error during matchmaking: " + e.getMessage());
        }
    }

}
