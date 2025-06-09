package com.skillbridge.entities;

//will implement enum in ApplicationDAO.

public class Application {
    //stu_id,int_id,status
    private int student_id;
    private int internship_id;
    private String status;
    public Application(int student_id,int internship_id,String status){
        this.student_id=student_id;
        this.internship_id=internship_id;
        this.status=status;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getInternship_id() {
        return internship_id;
    }

    public void setInternship_id(int internship_id) {
        this.internship_id = internship_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Application{" +
                "status='" + status + '\'' +
                '}';
    }
}
