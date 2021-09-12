package com.works.controller;

import com.works.exceptions.InvalidCredentials;
import com.works.exceptions.UnauthorizedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> handleIC (InvalidCredentials ex) {
        return new ResponseEntity<>("Got an exception: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> handleUU (UnauthorizedUser ex) {
        System.out.println("Got an exception: " + ex.getMessage());
        return new ResponseEntity<>("Got an exception: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}