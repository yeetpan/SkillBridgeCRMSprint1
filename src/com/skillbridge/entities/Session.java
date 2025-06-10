package com.skillbridge.entities;

public class Session {
    //slot_id, student_id, booking_status
    private int booking_id;
    private int slot_id;                    //references from mentor(lookup from mentor).
    private int student_id;
    private String booking_status;
    public Session(int slot_id,int student_id,String booking_status){
        this.slot_id=slot_id;
        this.student_id=student_id;
        this.booking_status=booking_status;
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
                ", student_id=" + student_id +
                ", booking_status='" + booking_status + '\'' +
                '}';
    }
}
