package com.skillbridge.entities;

public class StudentInterest {
    private int studentId;
    private int interestId;

    // Constructors
    public StudentInterest(int studentId, int interestId) {
        this.studentId = studentId;
        this.interestId = interestId;
    }

    public StudentInterest(){}

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getInterestId() {
        return interestId;
    }

    public void setInterestId(int interestId) {
        this.interestId = interestId;
    }

    // toString method
    @Override
    public String toString() {
        return "StudentInterest{" +
                "studentId=" + studentId +
                ", interestId=" + interestId +
                '}';
    }
}

