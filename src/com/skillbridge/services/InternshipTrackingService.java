package com.skillbridge.services;

import com.skillbridge.DAO.ApplicationDAO;
import com.skillbridge.entities.Application;

import java.util.ArrayList;

public class InternshipTrackingService {
    public static void Tracker(int student_id) {
        try {
            ArrayList<Application> applications = ApplicationDAO.getByStudentId(student_id);

            if (applications.isEmpty()) {
                System.out.println(" You have not applied to any internships yet.");
                return;
            }

            for (Application application : applications) {
                System.out.println(" Internship ID: " + application.getInternship_id() +
                        " | Status: " + application.getStatus());
            }

        } catch (Exception e) {
            System.err.println(" Error retrieving internship applications: " + e.getMessage());
        }
    }
}
