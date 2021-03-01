package com.alansantos.apiproduct.controllers.exception;

public class ErrorGenericException extends RuntimeException {
    public ErrorGenericException(String msg){
        super(msg);
    }
}
