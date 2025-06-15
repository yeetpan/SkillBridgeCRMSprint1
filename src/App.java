
import com.skillbridge.DAO.*;
import com.skillbridge.entities.*;
import com.skillbridge.services.InternshipTrackingService;
import com.skillbridge.services.SessionBookingService;
import com.skillbridge.util.EmailValidate;
import com.skillbridge.util.EmailException;
import com.skillbridge.util.StudentNotFoundException;

import java.sql.*;
import java.util.Optional;
import java.util.Scanner;
import java.util.ArrayList;

public class App {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, StudentNotFoundException {
        while (true) {
            System.out.println("\nWelcome to SkillBridge");
            System.out.println("1. Student");
            System.out.println("2. Mentor");
            System.out.println("3. View Internships");
            System.out.println("4. Register as Student");
            System.out.println("5. Register as Mentor");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> handleStudent();
                case 2 -> handleMentor();
                case 3 -> InternshipDAO.readInternship().forEach(System.out::println);
                case 4 -> createStudent();
                case 5 -> createMentor();
                case 6 -> {
                    System.out.println("Thank you for using SkillBridge!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // ====================== STUDENT FLOW ======================
    private static void handleStudent() throws SQLException, StudentNotFoundException {
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        int studentId = StudentDAO.GetStudentByEmail(email);

        if (studentId == -1) throw new StudentNotFoundException("Student Not Found!");

        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View My Applications");
            System.out.println("2. Apply for Internship");
            System.out.println("3. Book a Session");
            System.out.println("4. View My Sessions");
            System.out.println("5. Give Feedback");
            System.out.println("6. View My Feedback");
            System.out.println("7. Track My Internships");
            System.out.println("8.Back to Main Menu");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> ApplicationDAO.getByStudentId(studentId).forEach(System.out::println);
                case 2 -> {
                    InternshipDAO.readInternship().forEach(System.out::println);
                    System.out.print("Enter Internship ID to apply: ");
                    int internshipId = sc.nextInt(); sc.nextLine();
                    ApplicationDAO.createApplication(new Application(studentId, internshipId, "Applied"));
                }
                case 3 -> {
                    MatchMakingDAO.MatchMaker(studentId);
                    System.out.print("Enter Mentor ID to view available slots: ");
                    int mentorId = sc.nextInt(); sc.nextLine();

                    ArrayList<SessionSlot> slots = SessionSlotDAO.getAvailableByMentor(mentorId);
                    slots.forEach(System.out::println);

                    System.out.print("Enter Slot ID to book: ");
                    int slotId = sc.nextInt(); sc.nextLine();

                    String response = SessionBookingService.bookSession(studentId, mentorId, slotId);
                    System.out.println(response);
                }
                case 4 -> SessionDAO.getSessionsByStudent(studentId).forEach(System.out::println);
                case 5 -> {
                    ArrayList<Session> sessions=SessionDAO.getSessionsByBookingId(studentId);
                    for(Session session:sessions){
                        System.out.println(session.toString());
                    }
                    System.out.print("Enter Booking ID: ");
                    int bookingId = sc.nextInt();
                    System.out.print("Enter Rating (1-5): ");
                    int rating = sc.nextInt(); sc.nextLine();
                    System.out.print("Comments: ");
                    String comments = sc.nextLine();
                    FeedbackDAO.createFeedback(new Feedback(bookingId, studentId, rating, comments));
                }
                case 6 -> FeedbackDAO.getFeedbackByStudent(studentId).forEach(System.out::println);
                case 7->{
                    InternshipTrackingService.Tracker(studentId);
                }
                case 8 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ====================== MENTOR FLOW ======================
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
            System.out.println("3. View Feedback");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> SessionSlotDAO.getAvailableByMentor(mentorId).forEach(System.out::println);
                case 2 -> {
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    Date date = Date.valueOf(sc.nextLine());
                    System.out.print("Enter time (HH:mm:ss): ");
                    Time time = Time.valueOf(sc.nextLine());
                    System.out.print("Duration (minutes): ");
                    int duration = sc.nextInt(); sc.nextLine();

                    SessionSlot slot = new SessionSlot(0, mentorId, date, time, duration, "Available");
                    SessionSlotDAO.InsertSlot(slot);
                    System.out.println("Slot added.");
                }
                case 3 -> FeedbackDAO.getFeedbackByMentor(mentorId).forEach(System.out::println);
                case 4 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ====================== STUDENT REGISTRATION ======================
    private static void createStudent() throws SQLException {
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();

        String email;
        while (true) {
            System.out.print("Enter Your Email: ");
            email = sc.nextLine();
            try {
                if (!EmailValidate.isValid(email)) throw new EmailException("Invalid email format. Try again.");
                break;
            } catch (EmailException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Enter Your College: ");
        String college = sc.nextLine();

        InterestDAO.showInterests();
        System.out.print("Enter Interest ID: ");
        int interestId = sc.nextInt(); sc.nextLine();

        Student student = new Student(name, email, college);
        StudentDAO.createStudent(student);
        StudentInterestDAO.createStudentInterest(new StudentInterest(student.getStudent_id(), interestId));

        System.out.println("Match-making in Progress...");
        MatchMakingDAO.MatchMaker(student.getStudent_id());

        System.out.print("Enter Mentor ID to view available slots: ");
        int mentorId = sc.nextInt(); sc.nextLine();
        SessionSlotDAO.getAvailableByMentor(mentorId).forEach(System.out::println);

        System.out.print("Enter Slot ID to book: ");
        int slotId = sc.nextInt(); sc.nextLine();
        String response = SessionBookingService.bookSession(student.getStudent_id(), mentorId, slotId);
        System.out.println(response);

        System.out.print("Do you want to apply for Internships? (y/n): ");
        String opt = sc.nextLine().trim().toLowerCase();
        if (opt.equals("y")) {
            InternshipDAO.readInternship().forEach(System.out::println);
            System.out.print("Enter Internship ID: ");
            int internshipId = sc.nextInt(); sc.nextLine();
            ApplicationDAO.createApplication(new Application(student.getStudent_id(), internshipId, "Applied"));
        } else {
            System.out.println("See you soon!");
        }
    }

    // ====================== MENTOR REGISTRATION ======================
    private static void createMentor() {
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();

        String email;
        while (true) {
            System.out.print("Enter Email: ");
            email = sc.nextLine();
            try {
                if (!EmailValidate.isValid(email)) throw new EmailException("Invalid email format. Try again.");
                break;
            } catch (EmailException e) {
                System.out.println(e.getMessage());
            }
        }

        InterestDAO.showInterests();
        System.out.print("Enter Expertise ID: ");
        int expertiseId = sc.nextInt(); sc.nextLine();

        Mentor mentor = new Mentor(name, email, expertiseId);
        MentorDAO.createMentor(mentor);

        System.out.print("Do you want to create a session slot? (y/n): ");
        String opt = sc.nextLine().trim().toLowerCase();
        if (opt.equals("y")) {
            System.out.print("Enter date (yyyy-mm-dd): ");
            Date date = Date.valueOf(sc.nextLine());
            System.out.print("Enter time (HH:mm:ss): ");
            Time time = Time.valueOf(sc.nextLine());
            System.out.print("Enter duration in minutes: ");
            int duration = sc.nextInt(); sc.nextLine();
            SessionSlot slot = new SessionSlot(0, mentor.getMentor_id(), date, time, duration, "Available");
            SessionSlotDAO.InsertSlot(slot);
        } else {
            System.out.println("Session creation skipped.");
        }
    }
}
