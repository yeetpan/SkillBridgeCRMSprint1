package com.skillbridge.DAO;

import com.skillbridge.queries.InterestsQueries;
import com.skillbridge.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Used to display the interests to the user (mentor & student).
public class InterestDAO {

    public static void showInterests() {
        Connection con = DB.connect();
        if (con == null) {
            System.err.println("Database connection failed: Unable to display interests.");
            return;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(InterestsQueries.SHOW_INTERESTS);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("interest_id");
                String name = rs.getString("interest_name");
                System.out.println("Interest ID -> " + id + "  " + name);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error [showInterests]: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            DB.closeConnection(con);
        }
    }
}
