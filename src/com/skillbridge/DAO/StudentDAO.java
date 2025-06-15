package com.skillbridge.DAO;
import com.skillbridge.entities.Student;
import com.skillbridge.queries.StudentQueries;
import com.skillbridge.util.DB;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    public static void createStudent(Student student){
        try{
            Connection con = DB.connect();
            String query = StudentQueries.insert;
            PreparedStatement preparedStatement = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getStudent_name());
            preparedStatement.setString(2, student.getStudent_email());
            preparedStatement.setString(3, student.getStudent_college());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Student inserted successfully!!");
            }
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                student.setStudent_id(rs.getInt(1));
            }
            preparedStatement.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Student> readStudent() throws SQLException {
        ArrayList<Student> listofStudents = new ArrayList<>();
        Connection con = DB.connect();
        String query = StudentQueries.get_all;
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Student stu = new Student(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("college")
            );
            stu.setStudent_id(rs.getInt("student_id"));
            listofStudents.add(stu);
        }
        preparedStatement.close();
        return listofStudents;
    }

    public static void UpdateStudent(int student_id, String name, String email, String college){
        try {
            Connection con = DB.connect();
            String query = StudentQueries.update;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, college);
            preparedStatement.setInt(4, student_id);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Updated Successfully!!");
            }
            preparedStatement.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void DeleteStudent(int student_id){
        try {
            Connection con = DB.connect();
            String query = StudentQueries.delete_by_id;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, student_id); // You missed this
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Deleted Student with id " + student_id + " successfully!!");
            }
            preparedStatement.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static int GetStudentByEmail(String email) throws SQLException {
        Connection con = DB.connect();
        String query = StudentQueries.get_id;
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, email); // Required
        ResultSet rs = preparedStatement.executeQuery();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt("student_id");
        }
        preparedStatement.close();
        return id;
    }

    public static String GetStudentName(int studentId) throws SQLException{
        Connection con=DB.connect();
        String query=StudentQueries.get_name_by_id;
        PreparedStatement preparedStatement=con.prepareStatement(query);
        preparedStatement.setInt(1,studentId);
        ResultSet rs=preparedStatement.executeQuery();
        String stuname =rs.getString("name");
        return stuname;
    }


}
