package com.rest.restTask.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = {CustomClientServiceException.class})
    public ResponseEntity<Object> handleCustomeException(Exception ex){
        return new ResponseEntity<>(new ErrorResponseObject(ex.getMessage()),
        ((CustomClientServiceException) ex).getResponseCode());
    }
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleUnknownException(Exception ex) {
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return new ResponseEntity<>(new ErrorResponseObject(ex.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
        } else if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(new ErrorResponseObject("A null value has created an issue, the request could not be performed"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ErrorResponseObject("Unknown issue, please feel free to report"), HttpStatus.BAD_GATEWAY);
    }
}
