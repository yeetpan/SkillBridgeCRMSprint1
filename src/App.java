
import com.skillbridge.DAO.*;
import com.skillbridge.entities.*;
import com.skillbridge.services.SessionBookingService;
import com.skillbridge.util.EmailValidate;
import  com.skillbridge.util.EmailException;
import com.skillbridge.util.StudentNotFoundException;


import java.sql.SQLException;

import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;


public class App {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, StudentNotFoundException {
        while (true) {
            System.out.println("\nWelcome to SkillBridge");
            System.out.println("1. Student");
            System.out.println("2. Mentor");
            System.out.println("3. View Internships");
            System.out.println("4. Exit");
            System.out.println("5.Register as a Student");
            System.out.println("6.Register as a  Mentor");
            System.out.println("7.Exit");
            System.out.print("Enter your choice: ");
            int role = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (role) {
                case 1 -> handleStudent();
                case 2 -> handleMentor();
                case 3 -> InternshipDAO.readInternship().forEach(System.out::println);
                case 4 -> {
                    System.out.println("Thank you for using SkillBridge!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void handleStudent() throws SQLException, StudentNotFoundException {
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        int studentId = StudentDAO.GetStudentByEmail(email);

        if (studentId == -1) {
            throw new StudentNotFoundException("Student Not Found!");
        }

        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View My Applications");
            System.out.println("2. Apply for Internship");
            System.out.println("3. Book a Session");
            System.out.println("4. View My Sessions");
            System.out.println("5. Give Feedback");
            System.out.println("6. View My Feedback");
            System.out.println("7. Back to Main Menu");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> ApplicationDAO.getByStudentId(studentId).forEach(System.out::println);
                case 2 -> {
                    System.out.print("Enter Internship ID to apply: ");
                    int internship_id = sc.nextInt();
                    sc.nextLine();
                    ApplicationDAO.createApplication(new Application(studentId, internship_id, "Applied"));
                }
                case 3 -> {
                    //display the match

                    System.out.print("Enter Slot ID to book: ");
                    int slotId = sc.nextInt(); sc.nextLine();
                    SessionDAO.createSession(new Session());
                    SessionSlotDAO.updateStatus(slotId, "Booked");
                }
                case 4 -> SessionDAO.getSessionsByStudent(studentId).forEach(System.out::println);
                case 5 -> {
                    System.out.print("Enter Booking ID: ");
                    int bookingID = sc.nextInt();
                    System.out.print("Enter rating: ");
                    int rating = sc.nextInt(); sc.nextLine();
                    System.out.print("Comments: ");
                    String comments = sc.nextLine();
                    FeedbackDAO.createFeedback(new Feedback(bookingID, studentId, rating, comments));
                }
                case 6 -> FeedbackDAO.getFeedbackByStudent(studentId).forEach(System.out::println);
                case 7 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void handleMentor() throws SQLException {
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        int mentorId = MentorDAO.getMentorByEmail(email);

        if (mentorId == -1) {
            System.out.println("Mentor not found.");
            return;
        }

        while (true) {
            System.out.println("\n--- Mentor Menu ---");
            System.out.println("1. View My Session Slots");
            System.out.println("2. Add Slot");
            System.out.println("3.View Feedback By Students");
            System.out.println("4. Back to Main Menu");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> SessionSlotDAO.getAvailableByMentor(mentorId);
                case 2 -> {
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    String date = sc.nextLine();
                    System.out.print("Enter time (HH:mm:ss): ");
                    String time = sc.nextLine();
                    System.out.print("Duration in minutes: ");
                    int duration = sc.nextInt(); sc.nextLine();
                        SessionSlot slot = new SessionSlot (0,mentorId, Date.valueOf(date), Time.valueOf(time), duration, "Available");
                    SessionSlotDAO.InsertSlot(slot);
                }
                case 3 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void createStudent() throws  SQLException{
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Your Name!");
        String name=sc.nextLine();
        System.out.println("Enter Your E-mail");
        String email = "";
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter email: ");
            email = sc.nextLine();
            try {
                if (!EmailValidate.isValid(email)) {
                    throw new EmailException("Invalid email format. Try again.");
                }
                valid = true;
            } catch (EmailException e) {
                System.out.println(e.getMessage());
            }
        }
        String college=sc.nextLine();
        sc.next();
        InterestDAO.showInterests();

        Student student=new Student(name,email,college);
        System.out.println("Enter Your Interest_ID");
        int interest_id=sc.nextInt();
        StudentInterest studentInterest=new StudentInterest(student.getStudent_id(),interest_id);
        StudentInterestDAO.createStudentInterest(studentInterest);
        StudentDAO.createStudent(student);
        System.out.println("Match-making in Progress!!");
        MatchMakingDAO.MatchMaker(student.getStudent_id());
        System.out.println("Enter which mentor slot you need!");
        int mentor_id=sc.nextInt();
        ArrayList<SessionSlot> sessionSlots=SessionSlotDAO.getAvailableByMentor(mentor_id);
        for(SessionSlot sessionSlot:sessionSlots){
            System.out.println(sessionSlot.toString());
        }
        System.out.println("Enter Slot id to be booked");
        int slot_id=sc.nextInt();
        String comments= SessionBookingService.bookSession(student.getStudent_id(),mentor_id,slot_id);
        System.out.println(comments);
        System.out.println("Do you want to apply for Internships? (y/n)");
        String option=sc.nextLine();
        switch (option){
            case"y":
                ArrayList<Internship>internships=InternshipDAO.readInternship();
                for (Internship internship:internships){
                    System.out.println(internship.toString());
                }
                System.out.println("Enter Internship ID: ");
                int internship_id=sc.nextInt();
                Application application=new Application(student.getStudent_id(), internship_id,"Applied");
                ApplicationDAO.createApplication(application);
            case "n":
                System.out.println("See you soon!!");
        }

    }

    private static void createMentor(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Your Name!");
        String name=sc.nextLine();
        System.out.println("Enter Your E-mail");
        String email = "";
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter email: ");
            email = sc.nextLine();
            try {
                if (!EmailValidate.isValid(email)) {
                    throw new EmailException("Invalid email format. Try again.");
                }
                valid = true;
            } catch (EmailException e) {
                System.out.println(e.getMessage());
            }
        }
        InterestDAO.showInterests();
        System.out.println("Enter Expertise ID: ");
        int expertise_id=sc.nextInt();
        Mentor mentor=new Mentor(name,email,expertise_id);
        MentorDAO.createMentor(mentor);
        System.out.println("Do you want to create Sessions? (y/n)");
        String option=sc.nextLine();
        switch (option){
            case"y":
                SessionSlotDAO.InsertSlot();
            case "n":
                System.out.println("See you soon!!");
        }

    }

}
