package com.skillbridge.entities;

import java.sql.Date;
import java.sql.Time;

public class SessionSlot {
    private int slotId;
    private int mentorId;
    private Date date;
    private Time time;
    private int duration;
    private String status;

    public SessionSlot(){}

    public SessionSlot(int slotId,int mentorId,Date date,Time time,int duration,String status){
            this.slotId=slotId;
            this.mentorId=mentorId;
            this.date=date;
            this.time=time;
            this.duration=duration;
            this.status=status;
    }
    // Getters
    public int getSlotId() {
        return slotId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
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

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
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
                ", date=" + date +
                ", time=" + time +
                ", duration=" + duration +
                ", status='" + status + '\'' +
                '}';
    }
}
