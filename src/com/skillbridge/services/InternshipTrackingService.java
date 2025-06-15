package com.skillbridge.services;


import com.skillbridge.DAO.ApplicationDAO;
import com.skillbridge.entities.Application;

import java.sql.SQLException;
import java.util.ArrayList;

public class InternshipTrackingService {
    public static void Tracker(int student_id){
    try{
        ArrayList<Application>applications =ApplicationDAO.getByStudentId(student_id);
        for (Application application:applications){
            System.out.println(application.toString());
        }
    }catch (Exception e){
        System.err.println(e.getMessage());
    }
    }
}