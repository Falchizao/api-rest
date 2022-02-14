package com.example.posbanco.handler;

import com.example.posbanco.model.error.ErrorMessage;
import com.example.posbanco.model.exception.ResourceNotFound;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //This denotation will alarm the spring boot to handle w/ the exceptions we include in the methods
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class) //Every time something happens with the class ResourceNotFound, the spring will execute this code
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFound ex){
        ErrorMessage error = new ErrorMessage(ex.getMessage(), "Not Found", HttpStatus.NOT_FOUND.value()); //Personalized Message
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); //Response w/ status
    } 
    
}
