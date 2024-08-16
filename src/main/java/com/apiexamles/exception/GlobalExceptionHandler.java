package com.apiexamles.exception;

import com.apiexamles.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFound ex
    ) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(
            Exception ex
    )
    {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
