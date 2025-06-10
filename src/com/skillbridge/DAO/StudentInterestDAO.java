package com.skillbridge.DAO;

import com.skillbridge.entities.StudentInterest;
import com.skillbridge.queries.StudentInterestQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class StudentInterestDAO {

    public static void createStudentInterest(StudentInterest studentInterest) {
        try {
            Connection con = DB.connect();
            String query = StudentInterestQueries.INSERT;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, studentInterest.getStudentId());
            preparedStatement.setInt(2, studentInterest.getInterestId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student interest mapping inserted successfully!!");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Integer> getInterestsByStudent(int studentId) throws SQLException {
        ArrayList<Integer> interestIds = new ArrayList<>();
        Connection con = DB.connect();
        String query = StudentInterestQueries.GET_BY_STUDENT;
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, studentId);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            interestIds.add(rs.getInt("interest_id"));
        }
        preparedStatement.close();
        return interestIds;
    }

    public static void deleteStudentInterests(int studentId) {
        try {
            Connection con = DB.connect();
            String query = StudentInterestQueries.DELETE_BY_STUDENT;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted student interests for student ID " + studentId + " successfully!!");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Helper method to add multiple interests for a student
    public static void addMultipleInterests(int studentId, ArrayList<Integer> interestIds) {
        try {
            Connection con = DB.connect();
            String query = StudentInterestQueries.INSERT;
            PreparedStatement preparedStatement = con.prepareStatement(query);

            for (Integer interestId : interestIds) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, interestId);
                preparedStatement.addBatch();
            }

            int[] results = preparedStatement.executeBatch();
            System.out.println("Added " + results.length + " interests for student ID " + studentId);
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}