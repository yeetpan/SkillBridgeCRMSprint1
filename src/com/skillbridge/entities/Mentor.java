package com.skillbridge.entities;

public class Mentor {
    // add variables and standard getters,setters.
    private int mentor_id;              //PRIMARY KEY
    private String mentor_name;         //MENTOR NAME
    private String mentor_email;        //MENTOR EMAIL UNIQUE AND NOT NULL
    private int  expertise_id;          //FOREIGN KEY REFERENCES INTERESTS TABLE

    public  Mentor(String mentor_name,String mentor_email,int expertise_id){
        this.mentor_name=mentor_name;
        this.mentor_email=mentor_email;
        this.expertise_id=expertise_id;
    }

    public Mentor(){}

    public int getMentor_id() {
        return mentor_id;
    }

    public void setMentor_id(int mentor_id) {
        this.mentor_id = mentor_id;
    }

    public String getMentor_name() {
        return mentor_name;
    }

    public void setMentor_name(String mentor_name) {
        this.mentor_name = mentor_name;
    }

    public String getMentor_email() {
        return mentor_email;
    }

    public void setMentor_email(String mentor_email) {
        this.mentor_email = mentor_email;
    }

    public int getExpertise_id() {
        return expertise_id;
    }

    public void setExpertise_id(int expertise_id) {
        this.expertise_id = expertise_id;
    }
}
