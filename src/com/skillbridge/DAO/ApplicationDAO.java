package com.skillbridge.DAO;

import com.skillbridge.entities.Application;
import com.skillbridge.queries.ApplicationQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class ApplicationDAO {

    public static void createApplication(Application app) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection is null. Cannot create application.");
            return;
        }
        try {
            String query = ApplicationQueries.INSERT;
            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, app.getStudentId());
            preparedStatement.setInt(2, app.getInternshipId());
            preparedStatement.setString(3, app.getStatus());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Application inserted successfully!");
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    app.setApplicationId(rs.getInt(1));
                }
                rs.close();
            }

            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("SQL Error (createApplication): " + e.getMessage());
        } finally {
            DB.closeConnection(con);
        }
    }

    public static ArrayList<Application> getAllApplications() {
        ArrayList<Application> list = new ArrayList<>();
        Connection con = DB.connect();

        if (con == null) {
            System.err.println("Database connection is null. Cannot fetch applications.");
            return list;
        }

        try {
            String query = ApplicationQueries.GET_ALL;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Application app = new Application(
                        rs.getInt("student_id"),
                        rs.getInt("internship_id"),
                        rs.getString("status")
                );
                app.setApplicationId(rs.getInt("application_id"));
                list.add(app);
            }

            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("SQL Error (getAllApplications): " + e.getMessage());
        } finally {
            DB.closeConnection(con);
        }

        return list;
    }

    public static void updateApplicationStatus(int appId, String status) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection is null. Cannot update application status.");
            return;
        }

        try {
            String query = ApplicationQueries.UPDATE_STATUS;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, appId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Application status updated!");
            }

            ps.close();
        } catch (SQLException e) {
            System.err.println("SQL Error (updateApplicationStatus): " + e.getMessage());
        } finally {
            DB.closeConnection(con);
        }
    }

    public static ArrayList<Application> getByStudentId(int studentId) {
        ArrayList<Application> list = new ArrayList<>();
        Connection con = DB.connect();

        if (con == null) {
            System.err.println("Database connection is null. Cannot fetch applications by student ID.");
            return list;
        }

        try {
            String query = ApplicationQueries.GET_BY_STUDENT;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Application app = new Application(
                        rs.getInt("student_id"),
                        rs.getInt("internship_id"),
                        rs.getString("status")
                );
                app.setApplicationId(rs.getInt("application_id"));
                list.add(app);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("SQL Error (getByStudentId): " + e.getMessage());
        } finally {
            DB.closeConnection(con);
        }

        return list;
    }

    public static void deleteApplication(int appId) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection is null. Cannot delete application.");
            return;
        }

        try {
            String query = ApplicationQueries.DELETE_BY_ID;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, appId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Application deleted with ID: " + appId);
            }

            ps.close();
        } catch (SQLException e) {
            System.err.println("SQL Error (deleteApplication): " + e.getMessage());
        } finally {
            DB.closeConnection(con);
        }
    }
}
