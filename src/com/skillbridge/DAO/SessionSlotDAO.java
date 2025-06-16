package com.skillbridge.DAO ;

import com.skillbridge.util.DB;
import com.skillbridge.entities.SessionSlot;
import com.skillbridge.queries.SessionSlotQueries;

import java.sql.*;
import java.util.ArrayList;

public class SessionSlotDAO {

    public static void InsertSlot(SessionSlot slot) {
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(SessionSlotQueries.INSERT)) {

            ps.setInt(1, slot.getMentorId());
            ps.setDate(2, slot.getDate());
            ps.setTime(3, slot.getTime());
            ps.setInt(4, slot.getDuration());
            ps.setString(5, slot.getStatus());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Session slot inserted successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Insert failed: " + e.getMessage());
        }
    }

    public static ArrayList<SessionSlot> getAvailableByMentor(int mentorId) {
        ArrayList<SessionSlot> slots = new ArrayList<>();

        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(SessionSlotQueries.GET_AVAILABLE_BY_MENTOR,Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, mentorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SessionSlot sessionSlot=new SessionSlot();
                sessionSlot.setSlotId(rs.getInt("slot_id"));
                sessionSlot.setMentorId(rs.getInt("mentor_id"));
                sessionSlot.setDate(rs.getDate("date"));
                sessionSlot.setDuration(rs.getInt("duration"));
                sessionSlot.setTime(rs.getTime("time"));
                sessionSlot.setStatus(rs.getString("status"));
                slots.add(sessionSlot);
            }

        } catch (SQLException e) {
            System.err.println("Fetch available slots failed: " + e.getMessage());
        }

        return slots;
    }


    public static void updateStatus(int slotId, String newStatus) {
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(SessionSlotQueries.UPDATE_STATUS)) {

            ps.setString(1, newStatus);
            ps.setInt(2, slotId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Slot status updated.");
            } else {
                System.out.println("No slot found with ID: " + slotId);
            }
        } catch (SQLException e) {
            System.err.println("Status update failed: " + e.getMessage());
        }
    }
}
