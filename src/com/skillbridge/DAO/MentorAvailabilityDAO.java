package com.skillbridge.DAO;

import com.skillbridge.entities.MentorAvailability;
import com.skillbridge.queries.MentorAvailabilityQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class MentorAvailabilityDAO {

    public static void createAvailability(MentorAvailability availability) {
        try {
            Connection con = DB.connect();
            String query = MentorAvailabilityQueries.INSERT;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, availability.getMentorId());
            preparedStatement.setDate(2, availability.getDate());
            preparedStatement.setTime(3, availability.getStartTime());
            preparedStatement.setTime(4, availability.getEndTime());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Availability inserted successfully!!");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<MentorAvailability> getAvailabilityByMentor(int mentorId) throws SQLException {
        ArrayList<MentorAvailability> listOfAvailability = new ArrayList<>();
        Connection con = DB.connect();
        String query = MentorAvailabilityQueries.GET_BY_MENTOR;
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, mentorId);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            MentorAvailability availability = new MentorAvailability(
                    rs.getInt("mentor_id"),
                    rs.getDate("date"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time")
            );
            listOfAvailability.add(availability);
        }
        preparedStatement.close();
        return listOfAvailability;
    }

    public static ArrayList<MentorAvailability> getAllAvailableDates(int mentorId) throws SQLException {
        ArrayList<MentorAvailability> availabilityList = new ArrayList<>();
        Connection con = DB.connect();
        String query = MentorAvailabilityQueries.GET_ALL_AVAILABLE_DATES;
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, mentorId);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            MentorAvailability availability = new MentorAvailability(
                    rs.getInt("mentor_id"),
                    rs.getDate("date"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time")
            );
            availabilityList.add(availability);
        }
        preparedStatement.close();
        return availabilityList;
    }
}
