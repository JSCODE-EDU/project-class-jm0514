package com.toyboard.jeongmin.advice;

public class InternalServerException extends RuntimeException{

    static final String MESSAGE = "서버에 예상하지 못한 오류가 발생했습니다.";

    public InternalServerException() {
        super(MESSAGE);
    }
}
