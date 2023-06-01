package com.toyboard.jeongmin.member.exception;

import com.toyboard.jeongmin.advice.BadRequestException;

public class InvalidCredentialsException extends BadRequestException {

    private final static String MESSAGE = "아이디 또는 비밀번호를 확인해주세요.";

    public InvalidCredentialsException() {
        super(MESSAGE);
    }
}
