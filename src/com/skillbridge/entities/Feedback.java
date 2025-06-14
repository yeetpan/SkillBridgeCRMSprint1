package com.skillbridge.entities;

public class Feedback {
    private int feedbackId;
    private int bookingId;
    private Integer studentId;  // Nullable
    private int rating;
    private String comments;

    // Constructors
    public Feedback() {}

    public Feedback( int bookingId, Integer studentId, int rating, String comments) {
        this.bookingId = bookingId;
        this.studentId = studentId;
        this.rating = rating;
        this.comments = comments;
    }

    // Getters and Setters
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // toString
    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", bookingId=" + bookingId +
                ", studentId=" + studentId +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                '}';
    }
}

