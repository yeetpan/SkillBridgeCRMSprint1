package com.skillbridge.entities;
import java.sql.Date;
import java.sql.Time;
public class MentorAvailability {
    private int mentorId;
    private Date date;
    private Time startTime;
    private Time endTime;

    // Constructors
    public MentorAvailability() {}

    public MentorAvailability(int mentorId, Date date, Time startTime, Time endTime) {
        this.mentorId = mentorId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    // toString method
    @Override
    public String toString() {
        return "MentorAvailability{" +
                "mentorId=" + mentorId +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}