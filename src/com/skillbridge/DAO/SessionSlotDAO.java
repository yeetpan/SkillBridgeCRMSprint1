package com.skillbridge.DAO ;

import com.skillbridge.util.DB;
import com.skillbridge.entities.SessionSlot;
import com.skillbridge.queries.SessionSlotQueries;

import java.sql.*;
import java.util.ArrayList;

public class SessionSlotDAO {

    public static void insert(SessionSlot slot) {
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
             PreparedStatement ps = con.prepareStatement(SessionSlotQueries.GET_AVAILABLE_BY_MENTOR)) {

            ps.setInt(1, mentorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                slots.add(new SessionSlot(
                        rs.getInt("slot_id"),
                        rs.getInt("mentor_id"),
                        rs.getDate("date"),
                        rs.getTime("time"),
                        rs.getInt("duration"),
                        rs.getString("status")
                ));
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
