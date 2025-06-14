package com.skillbridge.entities;

//will implement enum in ApplicationDAO.

public class Application {
    //stu_id,int_id,status
    private int applicationId;
    private int studentId;
    private int internshipId;
    private String status;
    public Application(int studentId,int internshipId,String status){
        this.studentId=studentId;
        this.internshipId=internshipId;
        this.status=status;
    }
    // an empty constructor for some use-cases.
    public  Application(){}

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(int internshipId) {
        this.internshipId = internshipId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public String toString() {
        return "Application{" +
                "application_id=" + applicationId +
                ", student_id=" + studentId +
                ", internship_id=" + internshipId +
                ", status='" + status + '\'' +
                '}';
    }
}
