package com.skillbridge.DAO;

import com.skillbridge.entities.Student;
import com.skillbridge.queries.StudentQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    // Create a new student
    public static void createStudent(Student student) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to create student.");
            return;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(StudentQueries.insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getStudentEmail());
            ps.setString(3, student.getStudentCollege());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Student inserted successfully.");
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    student.setStudentId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [createStudent]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }
    }

    // Read all students
    public static ArrayList<Student> readStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to read students.");
            return students;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(StudentQueries.get_all);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("college")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [readStudents]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }

        return students;
    }

    // Update a student by ID
    public static void updateStudent(int studentId, String name, String email, String college) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to update student.");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(StudentQueries.update)) {
            try {
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, college);
                ps.setInt(4, studentId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("✅ Student updated successfully.");
                }
            } catch (SQLException e) {
                System.err.println("SQL Error [updateStudent]: " + e.getMessage());
            }
        } catch (Exception ignore) {
        } finally {
            DB.closeConnection(con);
        }
    }

    // Delete a student by ID
    public static void deleteStudent(int studentId) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to delete student.");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(StudentQueries.delete_by_id)) {
            try {
                ps.setInt(1, studentId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("🗑️ Deleted student with ID: " + studentId);
                }
            } catch (SQLException e) {
                System.err.println("SQL Error [deleteStudent]: " + e.getMessage());
            }
        } catch (Exception ignore) {
        } finally {
            DB.closeConnection(con);
        }
    }

    // Get student ID by email
    public static int getStudentIdByEmail(String email) {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to fetch student ID by email.");
            return -1;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        int studentId = -1;

        try {
            ps = con.prepareStatement(StudentQueries.get_id);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                studentId = rs.getInt("student_id");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [getStudentIdByEmail]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }

        return studentId;
    }
}
