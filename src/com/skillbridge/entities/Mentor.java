package com.skillbridge.entities;

public class Mentor {
    // add variables and standard getters,setters.
    private int mentorId;              //PRIMARY KEY
    private String mentorName;         //MENTOR NAME
    private String mentorEmail;        //MENTOR EMAIL UNIQUE AND NOT NULL
    private int  expertiseId;          //FOREIGN KEY REFERENCES INTERESTS TABLE

    public  Mentor(String mentorName,String mentorEmail,int expertiseId){
        this.mentorName=mentorName;
        this.mentorEmail=mentorEmail;
        this.expertiseId=expertiseId;
    }

    public Mentor(){}

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public int getExpertiseId() {
        return expertiseId;
    }

    public void setExpertiseId(int expertiseId) {
        this.expertiseId = expertiseId;
    }
}
