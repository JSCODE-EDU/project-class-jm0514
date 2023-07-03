package com.toyboard.jeongmin.advice;

public class InternalException extends BadRequestException {

    public InternalException(String message){
        super(message);
    }
}
