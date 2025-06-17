package com.skillbridge.entities;

import java.sql.Date;
import java.sql.Timestamp;  // Changed from Time to Timestamp

public class SessionSlot {
    private int slotId;
    private int mentorId;
    private Date slotDate;        // Changed from 'date' to 'slotDate'
    private Timestamp slotTime;   // Changed from 'time' to 'slotTime' and Time to Timestamp
    private int duration;
    private String status;

    public SessionSlot(){}

    public SessionSlot(int slotId, int mentorId, Date slotDate, Timestamp slotTime, int duration, String status){
        this.slotId = slotId;
        this.mentorId = mentorId;
        this.slotDate = slotDate;      // Updated parameter name
        this.slotTime = slotTime;      // Updated parameter name
        this.duration = duration;
        this.status = status;
    }

    // Getters
    public int getSlotId() {
        return slotId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public Date getSlotDate() {        // Updated getter name
        return slotDate;
    }

    public Timestamp getSlotTime() {   // Updated getter name and return type
        return slotTime;
    }

    public int getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public void setSlotDate(Date slotDate) {    // Updated setter name and parameter
        this.slotDate = slotDate;
    }

    public void setSlotTime(Timestamp slotTime) {  // Updated setter name, parameter, and type
        this.slotTime = slotTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString
    @Override
    public String toString() {
        return "SessionSlot{" +
                "slotId=" + slotId +
                ", mentorId=" + mentorId +
                ", slotDate=" + slotDate +      // Updated field name
                ", slotTime=" + slotTime +      // Updated field name
                ", duration=" + duration +
                ", status='" + status + '\'' +
                '}';
    }
}
