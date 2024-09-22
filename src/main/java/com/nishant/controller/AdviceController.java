package com.nishant.controller;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(Exception.class)
    public String testException(Exception exception) {
        return "exception occur";
    }

}
