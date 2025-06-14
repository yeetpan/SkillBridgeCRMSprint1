package com.skillbridge.DAO;

import com.skillbridge.entities.Internship;
import com.skillbridge.queries.InternshipQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class InternshipDAO {

    public static void createInternship(Internship internship) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to create internship.");
            return;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(InternshipQueries.INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, internship.getOrgName());
            ps.setString(2, internship.getTitle());
            ps.setInt(3, internship.getCapacity());
            ps.setString(4, internship.getDescription());
            ps.setDate(5, new java.sql.Date(internship.getDeadline().getTime()));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Internship inserted successfully.");
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    internship.setInternshipId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [createInternship]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }
    }

    public static ArrayList<Internship> readInternship() {
        ArrayList<Internship> internships = new ArrayList<>();
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to read internships.");
            return internships;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(InternshipQueries.GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Internship internship = new Internship(
                        rs.getString("org_name"),
                        rs.getString("title"),
                        rs.getInt("capacity"),
                        rs.getString("description"),
                        rs.getDate("deadline")
                );
                internship.setInternshipId(rs.getInt("internship_id"));
                internships.add(internship);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [readInternship]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }

        return internships;
    }

    public static Internship getInternshipById(int internshipId) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to retrieve internship.");
            return null;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        Internship internship = null;

        try {
            ps = con.prepareStatement(InternshipQueries.GET_BY_ID);
            ps.setInt(1, internshipId);
            rs = ps.executeQuery();

            if (rs.next()) {
                internship = new Internship(
                        rs.getString("org_name"),
                        rs.getString("title"),
                        rs.getInt("capacity"),
                        rs.getString("description"),
                        rs.getDate("deadline")
                );
                internship.setInternshipId(rs.getInt("internship_id"));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [getInternshipById]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }

        return internship;
    }

    public static void deleteInternship(int internshipId) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to delete internship.");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(InternshipQueries.DELETE_BY_ID)) {
            try {
                ps.setInt(1, internshipId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("🗑️ Internship deleted with ID: " + internshipId);
                }
            } catch (SQLException e) {
                System.err.println("SQL Error [deleteInternship]: " + e.getMessage());
            }
        } catch (Exception ignore) {
        } finally {
            DB.closeConnection(con);
        }
    }
}
