
import com.skillbridge.DAO.*;
import com.skillbridge.entities.*;
import com.skillbridge.util.StudentNotFoundException;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
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
        int studentId = StudentDAO.getStudentIdByEmail(email);

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
                    int iid = sc.nextInt();
                    sc.nextLine();
                    ApplicationDAO.createApplication(new Application(studentId, iid, "Applied"));
                }
                case 3 -> {
                    //display the match
                    System.out.print("Enter Slot ID to book: ");
                    int slotId = sc.nextInt(); sc.nextLine();
                    SessionDAO.createSession(new Session(slotId, studentId, "Scheduled"));
                    SessionSlotDAO.updateStatus(slotId, "Booked");
                }
                case 4 -> SessionDAO.getSessionsByStudent(studentId).forEach(System.out::println);
                case 5 -> {
                    System.out.print("Enter Booking ID: ");
                    int bid = sc.nextInt();
                    System.out.print("Enter rating: ");
                    int rating = sc.nextInt(); sc.nextLine();
                    System.out.print("Comments: ");
                    String comments = sc.nextLine();
                    FeedbackDAO.createFeedback(new Feedback(bid, studentId, rating, comments));
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
            System.out.println("3. Back to Main Menu");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> SessionSlotDAO.getAvailableByMentor(mentorId).forEach(System.out::println);
                case 2 -> {
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    String date = sc.nextLine();
                    System.out.print("Enter time (HH:mm:ss): ");
                    String time = sc.nextLine();
                    System.out.print("Duration in minutes: ");
                    int duration = sc.nextInt(); sc.nextLine();
                        SessionSlot slot = new SessionSlot(0, mentorId, Date.valueOf(date), Time.valueOf(time), duration, "Available");
                    SessionSlotDAO.insert(slot);
                }
                case 3 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
