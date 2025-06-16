package com.skillbridge.DAO;

import com.skillbridge.entities.Mentor;
import com.skillbridge.queries.MentorQueries;
import com.skillbridge.queries.StudentQueries;
import com.skillbridge.util.DB;


import java.sql.*;
import java.util.ArrayList;

public class MentorDAO {

    // Insert Mentor
    public static void createMentor(Mentor mentor) {
        try {
            Connection con = DB.connect();
            String query = MentorQueries.insert;
            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, mentor.getMentor_name());
            preparedStatement.setString(2, mentor.getMentor_email());
            preparedStatement.setInt(3, mentor.getExpertise_id());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Mentor inserted successfully!!");
            }
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                mentor.setMentor_id(rs.getInt(1));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Read All Mentors
    public static ArrayList<Mentor> readMentors() throws SQLException {
        ArrayList<Mentor> listOfMentors = new ArrayList<>();
        Connection con = DB.connect();
        String query = MentorQueries.get_all;
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Mentor mentor = new Mentor(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("expertise_id")
            );
            mentor.setMentor_id(rs.getInt("mentor_id"));
            listOfMentors.add(mentor);
        }
        preparedStatement.close();
        return listOfMentors;
    }

    // Update Mentor
    public static void updateMentor(int mentor_id, String name, String email, int expertise_id) {
        try {
            Connection con = DB.connect();
            String query = MentorQueries.UPDATE;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, expertise_id);
            preparedStatement.setInt(4, mentor_id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated mentor successfully!!");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete Mentor
    public static void deleteMentor(int mentor_id) {
        try {
            Connection con = DB.connect();
            String query = MentorQueries.DELETE_BY_ID;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, mentor_id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted mentor with id " + mentor_id + " successfully!!");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Get mentor_id by email
    public static int getMentorByEmail(String email) throws SQLException {
        Connection con = DB.connect();
        String query = MentorQueries.select_id_by_email;
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt("mentor_id");
        }
        preparedStatement.close();
        return id;
    }

    // Get mentors by expertise
    public static ArrayList<Mentor> getMentorsByExpertise(int expertiseId) throws SQLException {
        ArrayList<Mentor> matchedMentors = new ArrayList<>();
        Connection con = DB.connect();
        String query = MentorQueries.get_by_expertise;
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, expertiseId);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Mentor mentor = new Mentor(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("expertise_id")
            );
            mentor.setMentor_id(rs.getInt("mentor_id"));
            matchedMentors.add(mentor);
        }
        preparedStatement.close();
        return matchedMentors;
    }
    public static String GetMentorName(int mentorId) throws SQLException {
        try (Connection con = DB.connect();
             PreparedStatement preparedStatement = con.prepareStatement(MentorQueries.GET_NAME)) {
            preparedStatement.setInt(1, mentorId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    String mentorname = rs.getString("name");
                    return mentorname;
                } else {
                    throw new SQLException("No mentor found with ID: " + mentorId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching mentor name for mentorId " + mentorId + ": " + e.getMessage());
            throw e;
        }
    }
}
