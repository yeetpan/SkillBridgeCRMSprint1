package com.skillbridge.DAO;

import com.skillbridge.entities.Mentor;
import com.skillbridge.queries.MentorQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class MentorDAO {

    // Insert Mentor
    public static void createMentor(Mentor mentor) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to create mentor.");
            return;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(MentorQueries.insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, mentor.getMentorName());
            ps.setString(2, mentor.getMentorEmail());
            ps.setInt(3, mentor.getExpertiseId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Mentor inserted successfully.");
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    mentor.setMentorId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [createMentor]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }
    }

    // Read All Mentors
    public static ArrayList<Mentor> readMentors() {
        ArrayList<Mentor> mentorList = new ArrayList<>();
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to fetch mentors.");
            return mentorList;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(MentorQueries.get_all);
            rs = ps.executeQuery();

            while (rs.next()) {
                Mentor mentor = new Mentor(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("expertise_id")
                );
                mentor.setMentorId(rs.getInt("mentor_id"));
                mentorList.add(mentor);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [readMentors]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }

        return mentorList;
    }

    // Update Mentor
    public static void updateMentor(int mentorId, String name, String email, int expertiseId) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to update mentor.");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(MentorQueries.UPDATE)) {
            try {
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setInt(3, expertiseId);
                ps.setInt(4, mentorId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("✅ Mentor updated successfully.");
                }
            } catch (SQLException e) {
                System.err.println("SQL Error [updateMentor]: " + e.getMessage());
            }
        } catch (Exception ignore) {
        } finally {
            DB.closeConnection(con);
        }
    }

    // Delete Mentor
    public static void deleteMentor(int mentorId) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to delete mentor.");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(MentorQueries.DELETE_BY_ID)) {
            try {
                ps.setInt(1, mentorId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("🗑️ Mentor deleted with ID: " + mentorId);
                }
            } catch (SQLException e) {
                System.err.println("SQL Error [deleteMentor]: " + e.getMessage());
            }
        } catch (Exception ignore) {
        } finally {
            DB.closeConnection(con);
        }
    }

    // Get mentor ID by email
    public static int getMentorByEmail(String email) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to get mentor by email.");
            return -1;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        int mentorId = -1;

        try {
            ps = con.prepareStatement(MentorQueries.select_id_by_email);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                mentorId = rs.getInt("mentor_id");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [getMentorByEmail]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }

        return mentorId;
    }

    // Get mentors by expertise ID
    public static ArrayList<Mentor> getMentorsByExpertise(int expertiseId) {
        ArrayList<Mentor> mentorList = new ArrayList<>();
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to fetch mentors by expertise.");
            return mentorList;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(MentorQueries.get_by_expertise);
            ps.setInt(1, expertiseId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Mentor mentor = new Mentor(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("expertise_id")
                );
                mentor.setMentorId(rs.getInt("mentor_id"));
                mentorList.add(mentor);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [getMentorsByExpertise]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }

        return mentorList;
    }
}
