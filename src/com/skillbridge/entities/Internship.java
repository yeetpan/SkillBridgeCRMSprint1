package com.skillbridge.entities;

import java.util.Date;

public class Internship {
    private int internship_id;
    private String org_name;
    private String title;
    private int capacity;
    private String description;
    private Date deadline;

    public Internship(){}

    public Internship(String org_name,String title,int capacity,String description,Date deadline){
        this.org_name=org_name;
        this.title=title;
        this.capacity=capacity;
        this.description=description;
        this.deadline=deadline;
    }

    public int getInternship_id() {
        return internship_id;
    }

    public void setInternship_id(int internship_id) {
        this.internship_id = internship_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
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
                "internship_id=" + internship_id +
                ", org_name='" + org_name + '\'' +
                ", title='" + title + '\'' +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}

