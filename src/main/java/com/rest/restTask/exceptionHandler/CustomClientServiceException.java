package com.rest.restTask.exceptionHandler;

import org.springframework.http.HttpStatus;

public class CustomClientServiceException extends Exception{
    private final HttpStatus responseCode;

    public CustomClientServiceException(String message,HttpStatus code){
        super(message);
        this.responseCode = code;
    }

    public HttpStatus getResponseCode(){
        return responseCode;
    }

}
