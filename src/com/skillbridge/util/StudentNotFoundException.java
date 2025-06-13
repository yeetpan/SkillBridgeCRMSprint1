package com.skillbridge.util;
// Custom Exception Handling.
public class StudentNotFoundException extends Exception{
    public StudentNotFoundException(String message){
        super(message);
    }
}
