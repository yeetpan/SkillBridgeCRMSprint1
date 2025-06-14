package com.skillbridge.entities;
//add_mentor_id.
// what we can do is when matchmaking is done,we will show the mentors and their sessions and then let student select what mentor he wants then insert that data into Session.
public class Session {
    //slot_id, student_id, booking_status
    private int bookingId;
    private int slotId;                    //references from mentor(lookup from mentor).
    private int mentorId;
    private int studentId;
    private String bookingStatus;

    public Session(){}

    public Session(int slotId,int studentId,int mentorId,String bookingStatus){
        this.slotId=slotId;
        this.studentId=studentId;
        this.mentorId=mentorId;
        this.bookingStatus=bookingStatus;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Session{" +
                "booking_id=" + bookingId +
                ", slot_id=" + slotId +
                ", mentor_id=" + mentorId +
                ", student_id=" + studentId +
                ", booking_status='" + bookingStatus + '\'' +
                '}';
    }
}
