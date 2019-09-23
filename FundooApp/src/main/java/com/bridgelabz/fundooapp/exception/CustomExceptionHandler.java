package com.bridgelabz.fundooapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bridgelabz.fundooapp.responses.Response;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<Response> handleAllExceptions(Exception ex) {
       
    	Response response=new Response(ex.getMessage(),400);
      
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
}
