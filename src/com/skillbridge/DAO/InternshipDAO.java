package com.skillbridge.DAO;

import com.skillbridge.entities.Internship;
import com.skillbridge.queries.InternshipQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class InternshipDAO {

    public static void createInternship(Internship internship) {
        try {
            Connection con = DB.connect();
            String query = InternshipQueries.INSERT;
            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, internship.getOrg_name());
            preparedStatement.setString(2, internship.getTitle());
            preparedStatement.setInt(3, internship.getCapacity());
            preparedStatement.setString(4, internship.getDescription());
            preparedStatement.setDate(5, new java.sql.Date(internship.getDeadline().getTime()));
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Internship inserted successfully!!");
            }
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                internship.setInternship_id(rs.getInt(1));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Internship> readInternship() throws SQLException {
        ArrayList<Internship> listofInternships = new ArrayList<>();
        Connection con = DB.connect();
        String query = InternshipQueries.GET_ALL;
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Internship internship = new Internship(
                    rs.getString("org_name"),
                    rs.getString("title"),
                    rs.getInt("capacity"),
                    rs.getString("description"),
                    rs.getDate("deadline")
            );
            internship.setInternship_id(rs.getInt("internship_id"));
            listofInternships.add(internship);
        }
        preparedStatement.close();
        return listofInternships;
    }

    public static Internship getInternshipById(int internshipId) throws SQLException {
        Connection con = DB.connect();
        String query = InternshipQueries.GET_BY_ID;
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, internshipId);
        ResultSet rs = preparedStatement.executeQuery();
        Internship internship = null;
        if (rs.next()) {
            internship = new Internship(
                    rs.getString("org_name"),
                    rs.getString("title"),
                    rs.getInt("capacity"),
                    rs.getString("description"),
                    rs.getDate("deadline")
            );
            internship.setInternship_id(rs.getInt("internship_id"));
        }
        preparedStatement.close();
        return internship;
    }

    public static void DeleteInternship(int internshipId) {
        try {
            Connection con = DB.connect();
            String query = InternshipQueries.DELETE_BY_ID;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, internshipId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted Internship with id " + internshipId + " successfully!!");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

