package com.skillbridge.entities;

import java.util.Date;

public class Internship {
    private int internshipId;
    private String orgName;
    private String title;
    private int capacity;
    private String description;
    private Date deadline;

    public Internship(){}

    public Internship(String orgName,String title,int capacity,String description,Date deadline){
        this.orgName=orgName;
        this.title=title;
        this.capacity=capacity;
        this.description=description;
        this.deadline=deadline;
    }

    public int getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(int internshipId) {
        this.internshipId = internshipId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String org_name) {
        this.orgName = orgName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Internship{" +
                "internship_id=" + internshipId +
                ", org_name='" + orgName + '\'' +
                ", title='" + title + '\'' +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}

