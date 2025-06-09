package com.skillbridge.DAO;
import com.skillbridge.entities.Student;
import com.skillbridge.queries.StudentQueries;
import com.skillbridge.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO {
    // add DB CRUD ops
    public static void createStudent(Student student){
        try{
            Connection con= DB.connect();
            String query= StudentQueries.insert;
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,student.getStudent_name());
            preparedStatement.setString(2,student.getStudent_email());
            preparedStatement.setString(3,student.getStudent_college());
            int rowsAffected=preparedStatement.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Student inserted successfully!!");
            }
            preparedStatement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    //reads All Students present in DB.
    public static ArrayList<Student> readStudent() throws SQLException{

            ArrayList<Student> listofStudents = new ArrayList<Student>();
            Connection con = DB.connect();
            String query = StudentQueries.get_all;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Student stu = new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("email"), rs.getString("college"));
                listofStudents.add(stu);
            }
            preparedStatement.close();
            return listofStudents;
    }
    //update Student
    public static void UpdateStudent(int student_id,String name,String email,String college){
        try{
            Connection con=DB.connect();
            String query=StudentQueries.update;
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,college);
            preparedStatement.setInt(4,student_id);
            int rowsAffected=preparedStatement.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Updated Successfully!!");
            }
            preparedStatement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //Delete Student
    public static void DeleteStudent(int student_id){
        try{
        Connection con=DB.connect();
        String query=StudentQueries.delete_by_id;
        PreparedStatement preparedStatement=con.prepareStatement(query);
        int rowsAffected=preparedStatement.executeUpdate();
        if(rowsAffected>0){
            System.out.println("Deleted Student with id "+student_id+"successfully!!");
        }
    }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //Get Student_id by email
    public static int GetStudentByEmail(String email) throws  SQLException{
        Connection con=DB.connect();
        String query=StudentQueries.get_id;
        PreparedStatement preparedStatement=con.prepareStatement(query);
        ResultSet rs=preparedStatement.executeQuery();
        int id=rs.getInt("student_id");
        return  id;
    }
}


