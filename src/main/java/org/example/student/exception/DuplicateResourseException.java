package org.example.student.exception;

public class DuplicateResourseException extends RuntimeException{
    public DuplicateResourseException(String message){
        super(message);
    }
}
