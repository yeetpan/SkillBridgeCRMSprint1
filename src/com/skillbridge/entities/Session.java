package com.skillbridge.entities;
//add_mentor_id.
// what we can do is when matchmaking is done,we will show the mentors and their sessions and then let student select what mentor he wants then insert that data into Session.
public class Session {
    //slot_id, student_id, booking_status
    private int booking_id;
    private int slot_id;                    //references from mentor(lookup from mentor).
    private int mentor_id;
    private int student_id;
    private String booking_status;

    public Session(){}

    public Session(int slot_id,int student_id,int mentor_id,String booking_status){
        this.slot_id=slot_id;
        this.student_id=student_id;
        this.mentor_id=mentor_id;
        this.booking_status=booking_status;
    }

    public int getMentor_id() {
        return mentor_id;
    }

    public void setMentor_id(int mentor_id) {
        this.mentor_id = mentor_id;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    @Override
    public String toString() {
        return "Session{" +
                "booking_id=" + booking_id +
                ", slot_id=" + slot_id +
                ", mentor_id=" + mentor_id +
                ", student_id=" + student_id +
                ", booking_status='" + booking_status + '\'' +
                '}';
    }
}
