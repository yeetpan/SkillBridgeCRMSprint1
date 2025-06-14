package com.skillbridge.DAO;

import com.skillbridge.entities.StudentInterest;
import com.skillbridge.queries.StudentInterestQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class StudentInterestDAO {

    // Insert a single student-interest mapping
    public static void createStudentInterest(StudentInterest studentInterest) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to insert student interest.");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(StudentInterestQueries.INSERT)) {
            try {
                ps.setInt(1, studentInterest.getStudentId());
                ps.setInt(2, studentInterest.getInterestId());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("✅ Student interest mapping inserted successfully.");
                }
            } catch (SQLException e) {
                System.err.println("SQL Error [createStudentInterest]: " + e.getMessage());
            }
        } catch (Exception ignore) {
        } finally {
            DB.closeConnection(con);
        }
    }

    // Get all interest IDs for a specific student
    public static ArrayList<Integer> getInterestsByStudent(int studentId) {
        ArrayList<Integer> interestIds = new ArrayList<>();
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to fetch interests by student.");
            return interestIds;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(StudentInterestQueries.GET_BY_STUDENT);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();

            while (rs.next()) {
                interestIds.add(rs.getInt("interest_id"));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [getInterestsByStudent]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }

        return interestIds;
    }

    // Delete all interests for a student
    public static void deleteStudentInterests(int studentId) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to delete student interests.");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(StudentInterestQueries.DELETE_BY_STUDENT)) {
            try {
                ps.setInt(1, studentId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("🗑️ Deleted interests for student ID: " + studentId);
                }
            } catch (SQLException e) {
                System.err.println("SQL Error [deleteStudentInterests]: " + e.getMessage());
            }
        } catch (Exception ignore) {
        } finally {
            DB.closeConnection(con);
        }
    }

    // Add multiple interests in batch for a student
    public static void addMultipleInterests(int studentId, ArrayList<Integer> interestIds) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to add multiple interests.");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(StudentInterestQueries.INSERT)) {
            try {

                for (Integer interestId : interestIds) {
                    ps.setInt(1, studentId);
                    ps.setInt(2, interestId);
                    ps.addBatch();
                }

                int[] results = ps.executeBatch();
                System.out.println("✅ Added " + results.length + " interests for student ID: " + studentId);
            } catch (SQLException e) {
                System.err.println("SQL Error [addMultipleInterests]: " + e.getMessage());
            }
        } catch (Exception ignore) {
        } finally {
            DB.closeConnection(con);
        }
    }
}
