package com.toyboard.jeongmin.member.exception;

import com.toyboard.jeongmin.advice.BadRequestException;

public class NotFoundEmailException extends BadRequestException {

    private final static String MESSAGE = "존재하지 않는 이메일입니다.";

    public NotFoundEmailException() {
        super(MESSAGE);
    }
}
