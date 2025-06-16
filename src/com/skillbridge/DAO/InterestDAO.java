package com.skillbridge.DAO;
//InterestDAO
import com.skillbridge.queries.InterestsQueries;
import com.skillbridge.util.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//used to display the interests to the user(mentor & student).
public class InterestDAO {
    public  static void showInterests(){
        try{
            Connection con= DB.connect();
            PreparedStatement preparedStatement=con.prepareStatement(InterestsQueries.SHOW_INTERESTS);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                System.out.println("Interest id -> "+rs.getInt("interest_id")+ "  "+rs.getString("interest_name"));
            }
            preparedStatement.close();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
