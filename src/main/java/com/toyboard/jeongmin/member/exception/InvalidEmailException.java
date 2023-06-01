package com.toyboard.jeongmin.member.exception;

import com.toyboard.jeongmin.advice.BadRequestException;

public class InvalidEmailException extends BadRequestException {

    private static final String MESSAGE = "올바른 이메일 형식이 아닙니다.";

    public InvalidEmailException() {
        super(MESSAGE);
    }

}
